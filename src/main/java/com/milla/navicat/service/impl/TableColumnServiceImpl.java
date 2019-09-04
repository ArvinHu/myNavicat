package com.milla.navicat.service.impl;

import com.google.common.collect.Lists;
import com.milla.navicat.mapper.dynamic.SQLExecuteMapper;
import com.milla.navicat.mapper.dynamic.TableColumnDTOMapper;
import com.milla.navicat.pojo.dto.TableColumnDTO;
import com.milla.navicat.pojo.enums.EnumTableColumn;
import com.milla.navicat.pojo.enums.EnumTableColumnNumber;
import com.milla.navicat.pojo.vo.TableColumnVO;
import com.milla.navicat.service.ITableColumnService;
import com.milla.navicat.util.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.milla.navicat.constant.ValueConstant.V_YES_STRING;

/**
 * @Package: com.milla.navicat.service.impl
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/28 16:20
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/28 16:20
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Service
public class TableColumnServiceImpl implements ITableColumnService {
    @Autowired
    private TableColumnDTOMapper columnMapper;
    @Autowired
    private SQLExecuteMapper executeMapper;


    @Override
    public List<TableColumnVO> listColumnByTableName(String tableName) {
        String tableSchema = WebUtil.currentTableSchema();
        List<TableColumnDTO> columnDTOList = columnMapper.selectColumnListByTableName(tableSchema, tableName);
        if (CollectionUtils.isEmpty(columnDTOList)) {
            return null;
        }
        List<TableColumnVO> list = Lists.newArrayList();
        columnDTOList.stream().forEach(dto -> {
            TableColumnVO vo = new TableColumnVO();
            BeanUtils.copyProperties(dto, vo);
            settings(dto, vo);
            list.add(vo);
        });
        return list;
    }

    //实体转换参数分解
    private void settings(TableColumnDTO dto, TableColumnVO vo) {
        String columnType = dto.getColumnType();
        if (StringUtils.isNotBlank(columnType)) {
            if (StringUtils.containsIgnoreCase(columnType, EnumTableColumnNumber.unsigned.name())) {
                vo.setUnsigned(true);
            }
            if (StringUtils.containsIgnoreCase(columnType, EnumTableColumnNumber.zerofill.name())) {
                vo.setZerofill(true);
            }
            if (StringUtils.contains(columnType, "(")) {
                int i = columnType.indexOf(",");
                if (i == -1) {
                    vo.setDisplayWidth(Integer.parseInt(columnType.substring(columnType.indexOf("(") + 1, columnType.indexOf(")"))));
                } else {
                    vo.setDisplayWidth(Integer.parseInt(columnType.substring(columnType.indexOf("(") + 1, i)));
                    vo.setScale(Integer.parseInt(columnType.substring(i + 1, columnType.indexOf(")"))));
                }
            }
        }
        if (StringUtils.equalsIgnoreCase("PRI", dto.getColumnKey())) {
            vo.setPrimaryKey(true);
            if (StringUtils.equalsIgnoreCase("auto_increment", dto.getExtra())) {
                vo.setAutoIncrement(true);
            }
        }
        //是否能为空
        if (StringUtils.equalsIgnoreCase(V_YES_STRING, dto.getIsNullable())) {
            vo.setNullable(true);
        }
    }

    @Override
    public String validateTableColumn(TableColumnVO column) {
        Assert.isTrue(StringUtils.isNotBlank(column.getColumnName()), "字段名称不能为空");
        Assert.isTrue(column.getColumnCategory() != null, "字段类型不存在");
        Assert.isTrue(!(!column.isPrimaryKey() && column.isAutoIncrement()), "非主键字段不允许自增");
        Assert.isTrue(!(column.isPrimaryKey() && column.isAutoIncrement() && column.isNullable()), "自增字段不允许存在默认值");
        String columnName = column.getColumnName();
        EnumTableColumn category = column.getColumnCategory();
        Assert.notNull(columnName, "字段名称不能为空");
        Assert.notNull(category, "字段类型不能为空");
        StringBuilder sql = new StringBuilder(" ");
        if (column.isPrimaryKey()) {
            sql.append(String.format(" PRIMARY KEY (`%s`),", columnName));

        }
        sql.append("`").append(columnName).append("` ").append(category.name());
        numberCategory(sql, category, column);
        boolean isCharacter = characterCategory(sql, category, column);
        //是否为空
        if (!column.isNullable()) {
            sql.append(" NOT");
        }
        sql.append(" NULL");
        //是否有默认值
        if (StringUtils.isNotBlank(column.getColumnDefault())) {
            sql.append(" DEFAULT");
            if (isCharacter) {
                sql.append(" '").append(column.getColumnDefault()).append("'");
            } else {
                sql.append(" ").append(column.getColumnDefault());
            }
        }
        //是否有注释
        if (StringUtils.isNotBlank(column.getColumnComment())) {
            sql.append(" COMMENT").append(" '").append(column.getColumnComment()).append("'");
        }
        return sql.toString();
    }

    @Override
    public void removeTableColumn(TableColumnVO column) {
        Assert.isTrue(StringUtils.isNotBlank(column.getTableName()), "表格名称不能空");
        Assert.isTrue(StringUtils.isNotBlank(column.getColumnName()), "字段名称不能空");
        columnMapper.dropTableColumn(column);
    }

    //如果不是字符类型直接返回
    private boolean characterCategory(StringBuilder sql, EnumTableColumn category, TableColumnVO column) {
        boolean flag = category == EnumTableColumn.CHAR || category == EnumTableColumn.VARCHAR || category == EnumTableColumn.TINYBLOB || category == EnumTableColumn.TINYTEXT
                || category == EnumTableColumn.BLOB || category == EnumTableColumn.TEXT || category == EnumTableColumn.MEDIUMBLOB || category == EnumTableColumn.MEDIUMTEXT
                || category == EnumTableColumn.LONGBLOB || category == EnumTableColumn.LONGTEXT;
        if (!flag) return false;
        Assert.isTrue(column.getDisplayWidth() <= category.getLength(), "字节长度不能超过" + category.getLength());
        sql.append("(").append(column.getDisplayWidth()).append(")");
        //是否是二进制
        if (column.isBinary()) {
            sql.append(" BINARY");
        }
        //是否有字符集
        if (StringUtils.isNotBlank(column.getCharacterSetName())) {
            sql.append(" CHARACTER SET").append(column.getCharacterSetName());
            Assert.isTrue(StringUtils.isNotBlank(column.getCollationName()), "字符集排序规则不能为空");
            sql.append(" COLLATE").append(column.getCollationName());
        }
        return true;
    }

    //如果不是数字类型的直接返回
    private void numberCategory(StringBuilder sql, EnumTableColumn category, TableColumnVO column) {
        boolean numberFloat = category == EnumTableColumn.FLOAT || category == EnumTableColumn.DOUBLE || category == EnumTableColumn.DECIMAL;
        boolean numberInteger = category == EnumTableColumn.TINYINT || category == EnumTableColumn.SMALLINT || category == EnumTableColumn.MEDIUMINT || category == EnumTableColumn.INTEGER || category == EnumTableColumn.BIGINT;
        if (!numberFloat && !numberInteger) return;
        //有默认值的种类
        boolean hasDefault = numberInteger || category == EnumTableColumn.DECIMAL;
        Assert.isTrue(column.getDisplayWidth() <= category.getLength(), "显示宽度最多不能超过" + category.getLength());
        Assert.isTrue(column.getScale() <= category.getScale(), "小数位数最多不能超过30");
        sql.append("(").append((column.getDisplayWidth() == 0 && hasDefault) ? category.getDefaultLength() : column.getDisplayWidth());
        if (numberFloat) {
            sql.append(",").append(column.getScale());
        }
        sql.append(")");
        //是否有符号
        if (column.isUnsigned()) {
            sql.append(" UNSIGNED");
        }
        //是否用0填充
        if (column.isZerofill()) {
            sql.append(" ZEROFILL");
        }
        //是否自增长
        if (column.isAutoIncrement()) {
            sql.append(" AUTO_INCREMENT COMMENT");
        }
    }
}

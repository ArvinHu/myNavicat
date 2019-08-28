package com.milla.navicat.service.impl;

import com.milla.navicat.mapper.dynamic.TableDTOMapper;
import com.milla.navicat.pojo.enums.EnumTableColumn;
import com.milla.navicat.pojo.vo.TableColumnVO;
import com.milla.navicat.pojo.vo.TableVO;
import com.milla.navicat.service.ITableService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.milla.navicat.constant.ValueConstant.V_NO_STRING;

/**
 * @Package: com.milla.navicat.service.impl
 * @Description: <数据库表操作实现类>
 * @Author: MILLA
 * @CreateDate: 2019/8/27 10:54
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/27 10:54
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Service
public class TableServiceImpl implements ITableService {
    @Autowired
    private TableDTOMapper mapper;

    @Override
    public List<String> listTable() {
        return mapper.selectTableList();
    }

    @Override
    public void addTable(TableVO table) {
        Assert.notNull(table.getTableName(), "表格名称不能为空");
        Assert.notEmpty(table.getColumns(), "表格字段不能为空");
        StringBuilder sql = new StringBuilder("CREATE TABLE `" + table.getTableName() + "`  (\n");
        List<TableColumnVO> columns = table.getColumns();
        String primaryKey = null;
        for (int i = 0, len = columns.size(); i < len; i++) {
            TableColumnVO column = columns.get(i);
            String columnName = column.getColumnName();
            EnumTableColumn category = column.getColumnCategory();
            Assert.notNull(columnName, "字段名称不能为空");
            Assert.notNull(category, "字段类型不能为空");

            sql.append("  `").append(columnName).append("` ").append(category.name());
            characterCategory(sql, category, column);
            numberCategory(sql, category, column);
            //是否为空
            if (StringUtils.equalsIgnoreCase(V_NO_STRING, column.getIsNullable())) {
                sql.append(" NOT");
            }
            sql.append(" NULL");
            //是否有注释
            if (StringUtils.isNotBlank(column.getColumnComment())) {
                sql.append(" COMMENT").append(" '").append(column.getColumnComment()).append("'");
            }
            //不是最后一个
            if (i != len - 1) {
                sql.append(",\n");
            }
            if (column.isPrimaryKey()) {
                primaryKey = columnName;
            }
        }
        sql.append("  ,PRIMARY KEY (`").append(primaryKey).append("`)");
        mapper.alterTable(sql.toString());
    }

    @Override
    public void addTable(String sql) {
        mapper.alterTable(sql);
    }

    //如果不是字符类型直接返回
    private void characterCategory(StringBuilder sql, EnumTableColumn category, TableColumnVO column) {
        boolean flag = category == EnumTableColumn.CHAR || category == EnumTableColumn.VARCHAR || category == EnumTableColumn.TINYBLOB || category == EnumTableColumn.TINYTEXT
                || category == EnumTableColumn.BLOB || category == EnumTableColumn.TEXT || category == EnumTableColumn.MEDIUMBLOB || category == EnumTableColumn.MEDIUMTEXT
                || category == EnumTableColumn.LONGBLOB || category == EnumTableColumn.LONGTEXT;
        if (!flag) return;
        Assert.isTrue(column.getDisplayWidth() <= category.getLength(), "字节长度不能超过" + category.getLength());
        sql.append("(").append(category.getLength()).append(")");
        //是否有字符集
        if (StringUtils.isNotBlank(column.getCharacterSetName())) {
            sql.append(" CHARACTER SET").append(column.getCharacterSetName());
            Assert.isTrue(StringUtils.isNotBlank(column.getCollationName()), "字符集排序规则不能为空");
            sql.append(" COLLATE").append(column.getCollationName());
        }
    }

    //如果不是数字类型的直接返回
    private void numberCategory(StringBuilder sql, EnumTableColumn category, TableColumnVO column) {
        boolean numberFloat = category == EnumTableColumn.FLOAT || category == EnumTableColumn.DOUBLE || category == EnumTableColumn.DECIMAL;
        boolean numberInteger = category == EnumTableColumn.TINYINT || category == EnumTableColumn.SMALLINT || category == EnumTableColumn.MEDIUMINT || category == EnumTableColumn.INTEGER || category == EnumTableColumn.BIGINT;
        if (!numberFloat && !numberInteger) return;
        Assert.isTrue(column.getDisplayWidth() <= category.getLength(), "显示宽度最多不能超过" + category.getLength());
        Assert.isTrue(column.getScale() <= category.getScale(), "小数位数最多不能超过30");
        sql.append("(").append(category.getLength());
        if (numberFloat) {
            sql.append(",").append(category.getScale());
        }
        sql.append(")");
        //是否有符号
        if (column.isSigned()) {
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

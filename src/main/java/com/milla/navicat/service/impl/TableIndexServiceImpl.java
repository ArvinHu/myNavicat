package com.milla.navicat.service.impl;

import com.google.common.collect.Lists;
import com.milla.navicat.mapper.dynamic.SQLExecuteMapper;
import com.milla.navicat.mapper.dynamic.TableIndexDTOMapper;
import com.milla.navicat.pojo.dto.TableIndexDTO;
import com.milla.navicat.pojo.enums.EnumTableIndexCategory;
import com.milla.navicat.pojo.enums.EnumTableIndexFunction;
import com.milla.navicat.pojo.vo.TableIndexVO;
import com.milla.navicat.service.ITableIndexService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @Package: com.milla.navicat.service.impl
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/30 19:51
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/30 19:51
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Service
public class TableIndexServiceImpl implements ITableIndexService {

    @Autowired
    private TableIndexDTOMapper mapper;

    @Autowired
    private SQLExecuteMapper executeMapper;

    @Override
    public List<TableIndexVO> listIndexByTableName(String tableName) {
        List<TableIndexDTO> indexDTOList = mapper.selectIndexListByTableName(tableName);
        if (CollectionUtils.isEmpty(indexDTOList)) {
            return null;
        }
        List<TableIndexVO> list = Lists.newArrayList();
        indexDTOList.stream().forEach(dto -> {
            String keyName = dto.getKeyName();
            Optional<TableIndexVO> find = list.stream().filter(o -> StringUtils.equalsIgnoreCase(keyName, o.getKeyName())).findFirst();
            if (find != null && find.isPresent()) {
                find.get().setColumnName(find.get().getColumnName() + "," + dto.getColumnName());
                return;
            }
            TableIndexVO vo = new TableIndexVO();
            BeanUtils.copyProperties(dto, vo);
            settings(dto, vo);
            list.add(vo);
        });
        return list;
    }

    private void settings(TableIndexDTO dto, TableIndexVO vo) {
        //是否不唯一
        if (StringUtils.equalsIgnoreCase(EnumTableIndexCategory.FULLTEXT.name(), dto.getIndexType())) {
            vo.setIndexCategory(EnumTableIndexCategory.FULLTEXT);
            vo.setIndexFunction(null);
        }
        //Btree要么是唯一键要么是常规的
        if (StringUtils.equalsIgnoreCase(EnumTableIndexFunction.BTREE.name(), dto.getIndexType())) {
            vo.setIndexCategory(dto.isNonUnique() ? EnumTableIndexCategory.NORMAL : EnumTableIndexCategory.UNIQUE);
            vo.setIndexFunction(EnumTableIndexFunction.BTREE);
        }
    }

    @Override
    public String validateTableIndex(TableIndexVO column) {
        Assert.isTrue(StringUtils.isNotBlank(column.getColumnName()), "字段名称不能空");
        //默认索引名称为字段名称
        if (StringUtils.isBlank(column.getKeyName())) {
            column.setKeyName(column.getColumnName());
        }
        if (StringUtils.isBlank(column.getCollation())) {
            column.setCollation("A");
        }
        if (column.getCardinality() == 0) {
            column.setCardinality(1);
        }
        StringBuilder sb = new StringBuilder("  ");
        //索引种类
        if (column.getIndexCategory() != null) {
            sb.append(column.getIndexCategory().name());
        }
        sb.append(" INDEX `").append(column.getKeyName()).append("`(`").append(column.getColumnName()).append("`");
        //索引方法
        if (column.getIndexFunction() != null) {
            sb.append(" USING ").append(column.getIndexFunction().name());
        }
        //索引注释
        if (StringUtils.isNotBlank(column.getIndexComment())) {
            sb.append(" COMMENT '").append(column.getIndexComment()).append("'");
        }
        return sb.toString();
    }

    @Override
    public void removeTableIndex(TableIndexVO column) {
        Assert.isTrue(StringUtils.isNotBlank(column.getTable()), "表格名称不能空");
        Assert.isTrue(StringUtils.isNotBlank(column.getColumnName()), "字段名称不能空");
        mapper.alterTableIndex(column);
    }
}

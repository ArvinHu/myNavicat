package com.milla.navicat.service.impl;

import com.milla.navicat.mapper.dynamic.TableDTOMapper;
import com.milla.navicat.pojo.vo.TableColumnVO;
import com.milla.navicat.pojo.vo.TableVO;
import com.milla.navicat.service.ITableColumnService;
import com.milla.navicat.service.ITableService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

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
        //使用show命令的时候是有序的
        return mapper.selectTableList();
    }

    @Autowired
    private ITableColumnService service;

    @Override
    public void addTable(TableVO table) {
        Assert.notNull(table.getTableName(), "表格名称不能为空");
        Assert.notEmpty(table.getColumns(), "表格字段不能为空");
        StringBuilder sql = new StringBuilder("CREATE TABLE `" + table.getTableName() + "`  (\n");
        List<TableColumnVO> columns = table.getColumns();
        String primaryKey = null;
        for (int i = 0, len = columns.size(); i < len; i++) {
            TableColumnVO column = columns.get(i);
            //获取每一个字段的sql
            sql.append(service.validateTableColumn(column));
            //不是最后一个
            if (i != len - 1) {
                sql.append(",\n");
            }
            if (column.isPrimaryKey()) {
                primaryKey = column.getColumnName();
            }
        }
        if (StringUtils.isNotBlank(primaryKey)) {
            sql.append("  ,PRIMARY KEY (`").append(primaryKey).append("`)");
        }
        addTable(sql.toString());
    }

    @Override
    public void addTable(String sql) {
        mapper.createTable(sql);
    }

    @Override
    public void removeTable(String tableName) {
        Assert.isTrue(StringUtils.isNotBlank(tableName), "表格名称不能为空");
        mapper.dropTable(tableName);
    }

    @Override
    public void updateTable(String tableName, String newName) {
        Assert.isTrue(StringUtils.isNotBlank(tableName), "表格名称不能为空");
        Assert.isTrue(StringUtils.isNotBlank(newName), "表格新名称不能为空");
        List<String> tableList = listTable();
        Assert.isTrue(tableList == null || !tableList.contains(newName), String.format("表名'%s'已存在", newName));
        mapper.alterTableName(tableName, newName);
    }

    @Override
    public void removeTableData(String tableName) {
        Assert.isTrue(StringUtils.isNotBlank(tableName), "表格名称不能为空");
        mapper.deleteTableData(tableName);
    }

    @Override
    public void addTableCopy(String tableName, int category) {
        Assert.isTrue(StringUtils.isNotBlank(tableName), "表格名称不能为空");
        String newTableName = checkedExists(tableName);
        mapper.createTableOnlyStructureByCopy(tableName, newTableName);
        if (category == 0) {
            return;
        }
        mapper.createTableByCopy(tableName, newTableName);
    }

    @Override
    public void updateTableComment(String tableName, String tableComment) {
        Assert.isTrue(StringUtils.isNotBlank(tableName), "表格名称不能为空");
        mapper.alterTableCommentByName(tableName, tableComment);
    }

    private String checkedExists(String tableName) {
        List<String> tableList = listTable();
        for (int i = 0, len = tableList.size(); i < len; i++) {
            String newTableName = tableName + "_copy" + (i + 1);
            if (!tableList.contains(newTableName)) {
                return newTableName;
            }
        }
        return null;
    }
}

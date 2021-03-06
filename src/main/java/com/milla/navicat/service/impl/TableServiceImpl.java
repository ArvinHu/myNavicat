package com.milla.navicat.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.milla.navicat.comm.PageUtil;
import com.milla.navicat.comm.Query;
import com.milla.navicat.exception.DataSourceException;
import com.milla.navicat.mapper.dynamic.SQLExecuteMapper;
import com.milla.navicat.mapper.dynamic.TableDTOMapper;
import com.milla.navicat.pojo.vo.TableColumnVO;
import com.milla.navicat.pojo.vo.TableVO;
import com.milla.navicat.service.ITableColumnService;
import com.milla.navicat.service.ITableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
@Slf4j
@Service
public class TableServiceImpl implements ITableService {

    @Autowired
    private TableDTOMapper mapper;

    @Autowired
    private SQLExecuteMapper executeMapper;

    @Autowired
    private ITableColumnService service;

    @Override
    public List<String> listTable() {
        //使用show命令的时候是有序的
        return mapper.selectTableList();
    }

    @Override
    public void addTable(TableVO table) {
        Assert.hasText(table.getTableName(), "表格名称不能为空");
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
        Assert.hasText(sql, "SQL不能为空");
        executeMapper.updateBySQL(sql);
    }

    @Override
    public void removeTable(String tableName) {
        Assert.hasText(tableName, "表格名称不能为空");
        mapper.dropTable(tableName);
    }

    @Override
    public void updateTable(String tableName, String newName) {
        Assert.hasText(tableName, "表格名称不能为空");
        Assert.hasText(newName, "表格新名称不能为空");
        List<String> tableList = listTable();
        Assert.isTrue(tableList == null || !tableList.contains(newName), String.format("表名'%s'已存在", newName));
        mapper.alterTableName(tableName, newName);
    }

    @Override
    public void removeTableData(String tableName) {
        Assert.hasText(tableName, "表格名称不能为空");
        mapper.deleteTableData(tableName);
    }

    @Override
    public void addTableCopy(String tableName, int category) {
        Assert.hasText(tableName, "表格名称不能为空");
        String newTableName = checkedExists(tableName);
        mapper.createTableOnlyStructureByCopy(tableName, newTableName);
        if (category == 0) {
            return;
        }
        try {
            mapper.createTableByCopy(tableName, newTableName);
        } catch (Exception e) {
            log.error("复制表格出现异常", e);
            mapper.dropTable(newTableName);
            throw new DataSourceException("表格复制失败");
        }

    }

    @Override
    public void updateTableComment(String tableName, String tableComment) {
        Assert.hasText(tableName, "表格名称不能为空");
        mapper.alterTableCommentByName(tableName, tableComment);
    }

    @Override
    public Map<String, Object> listTableData(String tableName, Query query) {
        Assert.hasText(tableName, "表格名称不能为空");
        List<TableColumnVO> columnList = service.listColumnByTableName(tableName);
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(columnList)) {
            return null;
        }
        String[] columns = columnList.stream().map(TableColumnVO::getColumnName).distinct().toArray(String[]::new);
        String columnNameList = StringUtils.join(columns, ",");
        Page page = PageHelper.startPage(query.getPageNum(), query.getPageSize(), true);
        List<Map<String, Object>> list = mapper.selectTableDataList(tableName, columnNameList);
        PageUtil<List<Map<String, Object>>> pageData = new PageUtil<>(page.getTotal(), page.getPages(), page.getPageNum(), list);
        HashMap<String, Object> result = Maps.newHashMap();
        result.put("head", columns);
        result.put("pageData", pageData);
        return result;
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

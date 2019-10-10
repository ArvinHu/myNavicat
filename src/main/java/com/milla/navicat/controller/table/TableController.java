package com.milla.navicat.controller.table;

import com.milla.navicat.comm.Query;
import com.milla.navicat.pojo.vo.TableVO;
import com.milla.navicat.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.milla.navicat.controller.table
 * @Description: <数据库表操作>
 * @Author: MILLA
 * @CreateDate: 2019/8/27 10:47
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/27 10:47
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/tables", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TableController {
    @Autowired
    private ITableService tableService;

    @GetMapping(value = "/")
    public List<String> listTable() {
        return tableService.listTable();
    }

    @GetMapping(value = "/{tableName}/data")
    public Map<String, Object> listTableData(@PathVariable String tableName, Query query) {
        return tableService.listTableData(tableName, query);
    }

    @PostMapping(value = "/")
    public void addTable(@RequestBody TableVO table) {
        tableService.addTable(table);
    }

    //复制表 0:仅表格结构 1：表结构和数据
    @PostMapping(value = "/duplication")
    public void addTableCopy(String tableName, int category) {
        tableService.addTableCopy(tableName, category);
    }

    @DeleteMapping(value = "/{tableName}")
    public void removeTable(@PathVariable String tableName) {
        tableService.removeTable(tableName);
    }

    //清空表格
    @DeleteMapping(value = "/{tableName}/clear")
    public void removeTableData(@PathVariable String tableName) {
        tableService.removeTableData(tableName);
    }

    @PutMapping(value = "/{tableName}")
    public void updateTable(@PathVariable String tableName, String newName) {
        tableService.updateTable(tableName, newName);
    }

    @PutMapping(value = "/{tableName}/{tableComment}")
    public void updateTableComment(@PathVariable String tableName, @PathVariable String tableComment) {
        tableService.updateTableComment(tableName, tableComment);
    }


}

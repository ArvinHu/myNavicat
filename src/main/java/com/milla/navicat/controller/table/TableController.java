package com.milla.navicat.controller.table;

import com.milla.navicat.pojo.vo.TableVO;
import com.milla.navicat.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(value = "/")
    public void addTable(@RequestBody TableVO table) {
        tableService.addTable(table);
    }

    @PostMapping(value = "/sql")
    public void addTable(@RequestBody String sql) {
        tableService.addTable(sql);
    }

    //复制表 0:仅表格结构 1：表结构和数据
    @PostMapping(value = "/{tableName}")
    public void addTableCopy(@PathVariable String tableName, int category) {
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


}

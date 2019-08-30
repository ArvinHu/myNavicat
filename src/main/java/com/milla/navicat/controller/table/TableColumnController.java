package com.milla.navicat.controller.table;

import com.milla.navicat.pojo.vo.TableColumnVO;
import com.milla.navicat.service.ITableColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Package: com.milla.navicat.controller.table
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/28 16:11
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/28 16:11
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/columns", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TableColumnController {
    @Autowired
    private ITableColumnService service;

    @GetMapping(value = "/{tableName}")
    public List<TableColumnVO> listColumnByTableName(@PathVariable String tableName) {
        return service.listColumnByTableName(tableName);
    }

    //校验字段的种类长度
    @PostMapping(value = "")
    public String validateTableColumn(@RequestBody TableColumnVO column) {
        return service.validateTableColumn(column);
    }
}

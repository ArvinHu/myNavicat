package com.milla.navicat.controller.table;

import com.milla.navicat.pojo.vo.TableColumnVO;
import com.milla.navicat.service.ITableColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping(value = "/fields", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TableColumnController {
    @Autowired
    private ITableColumnService service;

    @GetMapping(value = "/{tableName}")
    public List<TableColumnVO> listFieldsByTableName(@PathVariable String tableName) {
        return service.listFieldsByTableName(tableName);
    }
}

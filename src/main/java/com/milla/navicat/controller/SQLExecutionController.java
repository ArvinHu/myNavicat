package com.milla.navicat.controller;

import com.milla.navicat.service.ISQLExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.milla.navicat.controller
 * @Description: <执行sql语句>
 * @Author: MILLA
 * @CreateDate: 2019/8/15 19:00
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/15 19:00
 * @UpdateRemark: <>
 * @Version: 1.0
 */

@RestController
@RequestMapping(value = "/execution", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SQLExecutionController {

    @Autowired
    private ISQLExecutionService service;

    @PutMapping(value = "")
    public void updateByExecuteSQL(@RequestBody String sql) {
        service.updateByExecuteSQL(sql);
    }

    @PostMapping(value = "/")
    public List<Map<Object, Object>> listObjectByExecuteSQL(@RequestBody String sql) {
        return service.listObjectByExecuteSQL(sql);
    }
}

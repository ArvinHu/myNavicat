package com.milla.navicat.controller;

import com.milla.navicat.comm.ResponseData;
import com.milla.navicat.config.datasource.dynamic.DataSourceVO;
import com.milla.navicat.service.IShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Package: com.milla.navicat.controller
 * @Description: <显示数据库、表、视图>
 * @Author: MILLA
 * @CreateDate: 2019/8/15 19:00
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/15 19:00
 * @UpdateRemark: <>
 * @Version: 1.0
 */

@RestController
@RequestMapping(value = "/show", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ShowController {
    @Autowired
    IShowService service;

    @GetMapping(value = "/databases")
    public List<String> listDatabase(DataSourceVO dataSource) {
        return null;
    }

    @GetMapping(value = "/{connId}/encodings")
    public List<String> listCharacterEncoding(@PathVariable Integer connId) {
        return service.listCharacterEncoding(connId);
    }

    @GetMapping(value = "/{connId}/encodings/{character}")
    public List<String> listOrderingRuleByCharacterEncoding(@PathVariable Integer connId, @PathVariable String character) {
        return service.listOrderingRuleByCharacterEncoding(connId, character);
    }

    @GetMapping(value = "/tables")
    public ResponseData listTable(DataSourceVO dataSource) {
        return service.listTable();
    }

    @GetMapping(value = "/views")
    public ResponseData listView() {
        return service.listView();
    }

    @GetMapping(value = "/functions")
    public ResponseData listFunction() {
        return service.listFunction();
    }

    @GetMapping(value = "/events")
    public ResponseData listEvent() {
        return service.listEvent();
    }

}

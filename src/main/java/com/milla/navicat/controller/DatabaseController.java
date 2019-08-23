package com.milla.navicat.controller;

import com.milla.navicat.config.datasource.dynamic.DataSourceVO;
import com.milla.navicat.service.IDatabaseService;
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
 * @Description: <连接下的数据库操作>
 * @Author: MILLA
 * @CreateDate: 2019/8/23 9:30
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/23 9:30
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/databases", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DatabaseController {
    @Autowired
    private IShowService showService;
    @Autowired
    private IDatabaseService databaseService;

    @GetMapping(value = "")
    public List<String> listDatabase(DataSourceVO dataSource) {
        return showService.listDatabase(dataSource);
    }

    @GetMapping(value = "/{datasourceId}")
    public void changeDatabase(@PathVariable String datasourceId) {
        databaseService.changeDatabase(datasourceId);
    }
}

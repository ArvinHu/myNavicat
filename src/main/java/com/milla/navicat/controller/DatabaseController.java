package com.milla.navicat.controller;

import com.milla.navicat.pojo.vo.DatabaseVO;
import com.milla.navicat.service.IDatabaseService;
import com.milla.navicat.service.IShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{connId}")
    public List<String> listDatabase(@PathVariable Integer connId) {
        return showService.listDatabase(connId);
    }

    @GetMapping(value = "/{connId}/{databaseName}")
    public DatabaseVO getDatabaseByDatabaseName(@PathVariable Integer connId, @PathVariable String databaseName) {
        return showService.getDatabaseByDatabaseName(connId, databaseName);
    }

    @PostMapping(value = "")
    public int addDatabase(@RequestBody @Validated DatabaseVO database) {
        return databaseService.addDatabase(database);
    }

    @PutMapping(value = "/{connId}")
    public int updateDatabase(@PathVariable Integer connId, @RequestBody @Validated DatabaseVO database) {
        database.setConnId(connId);
        return databaseService.updateDatabase(database);
    }

    @DeleteMapping(value = "")
    public int removeDatabase(@RequestBody @Validated DatabaseVO database) {
        return databaseService.removeDatabase(database);
    }

    @PutMapping(value = "")
    public void changeDatabase(@RequestBody DatabaseVO database) {
        databaseService.changeDatabase(database.getConnId(), database.getDatabaseName());
    }
}

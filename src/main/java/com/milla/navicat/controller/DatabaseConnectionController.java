package com.milla.navicat.controller;

import com.milla.navicat.pojo.dto.DatabaseConnectionDTO;
import com.milla.navicat.service.IDatabaseConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Package: com.milla.navicat.controller
 * @Description: <数据库连接类>
 * @Author: MILLA
 * @CreateDate: 2019/8/15 18:37
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/15 18:37
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/connections", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DatabaseConnectionController {
    @Autowired
    private IDatabaseConnectionService service;

    @GetMapping(value = "")
    public List<DatabaseConnectionDTO> listConnection() {
        return service.listConnection();
    }

    @GetMapping(value = "/{connId}")
    public DatabaseConnectionDTO getConnection(@PathVariable Integer connId) {
        return service.getConnectionByConnId(connId);
    }

    @GetMapping(value = "/test/{connId}")
    public boolean testConnection(@PathVariable Integer connId) {
        return service.testConnection(connId);
    }

    @PostMapping(value = "")
    public void addConnection(@RequestBody @Validated DatabaseConnectionDTO conn) {
        service.addConnection(conn);
    }

    @PutMapping(value = "/{id}")
    public int updateConnection(DatabaseConnectionDTO conn, @PathVariable Integer id) {
        conn.setId(id);
        return service.updateConnection(conn);
    }

    @DeleteMapping(value = "/{id}")
    public int removeConnection(@PathVariable Integer id) {
        return service.removeConnection(id);
    }
}

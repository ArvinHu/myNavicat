package com.milla.navicat.controller;

import com.milla.navicat.comm.ResponseData;
import com.milla.navicat.pojo.dto.ConnectionDTO;
import com.milla.navicat.service.IDatabaseConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/")
    public ResponseData listConnection(@RequestHeader String token) {
        return service.listConnection();
    }

    @GetMapping(value = "/{connId}")
    public ResponseData getConnection(@PathVariable Integer connId) {
        return service.getConnection(connId);
    }

    @GetMapping(value = "/test/")
    public ResponseData testConnection(ConnectionDTO conn) {
        return service.testConnection(conn);
    }

    @PostMapping(value = "/")
    public ResponseData addConnection(ConnectionDTO conn) {
        return service.addConnection(conn);
    }

    @PutMapping(value = "/{connId}")
    public ResponseData updateConnection(ConnectionDTO conn, @PathVariable Integer connId) {
        conn.setConnId(connId);
        return service.updateConnection(conn);
    }

    @DeleteMapping(value = "/{connId}")
    public ResponseData removeConnection(@PathVariable Integer connId) {
        return service.removeConnection(connId);
    }
}

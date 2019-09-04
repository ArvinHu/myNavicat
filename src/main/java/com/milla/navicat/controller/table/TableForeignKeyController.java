package com.milla.navicat.controller.table;

import com.milla.navicat.pojo.dto.TableForeignKeyDTO;
import com.milla.navicat.service.ITableForeignKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Package: com.milla.navicat.controller.table
 * @Description: <外键控制类>
 * @Author: MILLA
 * @CreateDate: 2019/9/2 16:26
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/9/2 16:26
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/keys", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TableForeignKeyController {
    @Autowired
    private ITableForeignKeyService service;

    @GetMapping(value = "/{tableName}")
    public List<TableForeignKeyDTO> listForeignKeyByTableName(@PathVariable String tableName) {
        return service.listForeignKeyByTableName(tableName);
    }

    @PostMapping(value = "/")
    public String validateTableForeignKey(@RequestBody TableForeignKeyDTO dto) {
        return service.validateTableForeignKey(dto);
    }
}

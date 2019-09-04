package com.milla.navicat.controller.table;

import com.milla.navicat.pojo.vo.TableIndexVO;
import com.milla.navicat.service.ITableIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Package: com.milla.navicat.controller.table
 * @Description: <表格索引控制类>
 * @Author: MILLA
 * @CreateDate: 2019/8/30 19:46
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/30 19:46
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/indexes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TableIndexController {
    @Autowired
    private ITableIndexService service;

    @GetMapping(value = "/{tableName}")
    public List<TableIndexVO> listIndexByTableName(@PathVariable String tableName) {
        return service.listIndexByTableName(tableName);
    }

    //校验并生成创建索引的sql
    @PostMapping(value = "")
    public String validateTableIndex(@RequestBody TableIndexVO column) {
        return service.validateTableIndex(column);
    }

    @PostMapping(value = "")
    public void updateTableIndex(@RequestBody String sql) {
        service.updateTableIndex(sql);
    }

    //校验并生成创建索引的sql
    @DeleteMapping(value = "")
    public void removeTableIndex(@RequestBody TableIndexVO column) {
        service.removeTableIndex(column);
    }
}
/**
 * CREATE TABLE `tcc_order`.`Untitled`  (
 * `id` int(0) AS () COMMENT '主键' NOT NULL,
 * `name` varchar(255) BINARY CHARACTER SET binary COLLATE binary NULL COMMENT '名称',
 * `address` varchar(255) NULL,
 * `socre` int(0) NULL COMMENT '分数',
 * PRIMARY KEY (`id`),
 * INDEX `name`(`name`) USING BTREE COMMENT '普通索引',
 * FULLTEXT INDEX `all`(`address`, `socre`) USING HASH COMMENT '联合索引',
 * FOREIGN KEY (`id`) REFERENCES `tcc_order`.()
 * );
 * <p>
 * CREATE TRIGGER BEFORE UPDATE ON `Untitled` FOR EACH ROW;
 * <p>
 * CREATE TRIGGER AFTER UPDATE ON `Untitled` FOR EACH ROW;
 */
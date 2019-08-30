package com.milla.navicat.controller.table;

import com.milla.navicat.pojo.vo.TableColumnVO;
import com.milla.navicat.pojo.vo.TableIndexVO;
import com.milla.navicat.service.ITableColumnService;
import com.milla.navicat.service.ITableIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

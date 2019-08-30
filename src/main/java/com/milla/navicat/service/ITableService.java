package com.milla.navicat.service;

import com.milla.navicat.pojo.vo.TableVO;

import java.util.List;

/**
 * @Package: com.milla.navicat.service
 * @Description: <数据库表操作类>
 * @Author: MILLA
 * @CreateDate: 2019/8/27 10:53
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/27 10:53
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public interface ITableService {
    /**
     * 获取所有的数据表
     *
     * @return
     */
    List<String> listTable();

    void addTable(TableVO table);

    void addTable(String sql);

    void removeTable(String tableName);

    void updateTable(String tableName, String newName);

    void removeTableData(String tableName);

    void addTableCopy(String tableName, int category);
}

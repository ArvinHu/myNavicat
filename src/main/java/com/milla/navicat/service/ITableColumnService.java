package com.milla.navicat.service;

import com.milla.navicat.pojo.vo.TableColumnVO;

import java.util.List;

/**
 * @Package: com.milla.navicat.service
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/28 16:13
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/28 16:13
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public interface ITableColumnService {
    List<TableColumnVO> listColumnByTableName(String tableName);

    String validateTableColumn(TableColumnVO column);

    void removeTableColumn(TableColumnVO column);

    void updateTableColumn(String sql);
}

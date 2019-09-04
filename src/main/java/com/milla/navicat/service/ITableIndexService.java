package com.milla.navicat.service;

import com.milla.navicat.pojo.vo.TableIndexVO;

import java.util.List;

/**
 * @Package: com.milla.navicat.service
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/30 19:49
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/30 19:49
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public interface ITableIndexService {
    List<TableIndexVO> listIndexByTableName(String tableName);

    String validateTableIndex(TableIndexVO column);

    void removeTableIndex(TableIndexVO column);
}

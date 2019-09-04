package com.milla.navicat.service;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.milla.navicat.service
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/9/4 9:35
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/9/4 9:35
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public interface ISQLExecutionService {
    void updateByExecuteSQL(String sql);

    List<Map<Object, Object>> listObjectByExecuteSQL(String sql);
}

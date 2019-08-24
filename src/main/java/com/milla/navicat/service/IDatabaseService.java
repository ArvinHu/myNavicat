package com.milla.navicat.service;

import com.milla.navicat.config.datasource.dynamic.DataSourceVO;

/**
 * @Package: com.milla.navicat.service
 * @Description: <数据库操作类>
 * @Author: MILLA
 * @CreateDate: 2019/8/23 9:34
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/23 9:34
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public interface IDatabaseService {

    void changeDatabase(DataSourceVO datasource);
}

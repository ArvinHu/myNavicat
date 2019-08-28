package com.milla.navicat.service;

import com.milla.navicat.pojo.vo.DatabaseVO;

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

    /**
     * 新增数据库
     *
     * @param database
     * @return
     */
    int addDatabase(DatabaseVO database);

    /**
     * 修改数据库
     *
     * @param database
     * @return
     */
    int updateDatabase(DatabaseVO database);

    /**
     * 删除数据库
     *
     * @param database
     * @return
     */
    int removeDatabase(DatabaseVO database);

    void changeDatabase(String datasourceId);
}

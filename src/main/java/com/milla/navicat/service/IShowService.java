package com.milla.navicat.service;

import com.milla.navicat.comm.ResponseData;
import com.milla.navicat.config.datasource.dynamic.DataSourceVO;
import com.milla.navicat.pojo.vo.DatabaseVO;

import java.util.List;

/**
 * @Package: com.milla.navicat.service
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/15 19:24
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/15 19:24
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public interface IShowService {
    List<String> listDatabase(Integer connId);

    ResponseData listTable();

    ResponseData listView();

    ResponseData listFunction();

    ResponseData listEvent();

    /**
     * 根据连接id查询字符集信息
     *
     * @param connId
     * @return
     */
    List<String> listCharacterEncoding(Integer connId);

    /**
     * 根据字符集查询排序规则信息
     *
     * @param connId
     * @param character
     * @return
     */
    List<String> listOrderingRuleByCharacterEncoding(Integer connId, String character);

    /**
     * 数据库详情信息
     *
     * @param connId
     * @param databaseName
     * @return
     */
    DatabaseVO getDatabaseByDatabaseName(Integer connId, String databaseName);
}

package com.milla.navicat.service;

import com.milla.navicat.comm.ResponseData;
import com.milla.navicat.config.datasource.dynamic.DataSourceVO;

import java.util.List;
import java.util.Set;

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

    ResponseData listTable(DataSourceVO dataSource);

    ResponseData listView();

    ResponseData listFunction();

    ResponseData listEvent();

    List<String> listCharacterEncoding(Integer connId);

    List<String> listOrderingRuleByCharacterEncoding(Integer connId, String character);
}

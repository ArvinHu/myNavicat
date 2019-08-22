package com.milla.navicat.service;

import com.milla.navicat.comm.ResponseData;

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
    ResponseData listDatabase();

    ResponseData listTable();

    ResponseData listView();

    ResponseData listFunction();

    ResponseData listEvent();

}

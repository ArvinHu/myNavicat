package com.milla.navicat.service;

import com.milla.navicat.comm.ResponseData;
import com.milla.navicat.pojo.dto.ConnectionDTO;

/**
 * @Package: com.milla.navicat.service
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/15 18:48
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/15 18:48
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public interface IDatabaseConnectionService {

    ResponseData listConnection();

    ResponseData getConnection(Integer connId);

    ResponseData testConnection(ConnectionDTO conn);

    ResponseData addConnection(ConnectionDTO conn);

    ResponseData updateConnection(ConnectionDTO conn);

    ResponseData removeConnection(Integer connId);
}

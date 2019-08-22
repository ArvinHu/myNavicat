package com.milla.navicat.service.impl;

import com.milla.navicat.comm.ResponseData;
import com.milla.navicat.pojo.dto.ConnectionDTO;
import com.milla.navicat.service.IDatabaseConnectionService;
import org.springframework.stereotype.Service;

/**
 * @Package: com.milla.navicat.service.impl
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/16 14:09
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/16 14:09
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Service
public class DatabaseConnectionServiceImpl implements IDatabaseConnectionService {
    @Override
    public ResponseData listConnection() {
        return null;
    }

    @Override
    public ResponseData getConnection(Integer connId) {
        return null;
    }

    @Override
    public ResponseData testConnection(ConnectionDTO conn) {
        return null;
    }

    @Override
    public ResponseData addConnection(ConnectionDTO conn) {
        return null;
    }

    @Override
    public ResponseData updateConnection(ConnectionDTO conn) {
        return null;
    }

    @Override
    public ResponseData removeConnection(Integer connId) {
        return null;
    }
}

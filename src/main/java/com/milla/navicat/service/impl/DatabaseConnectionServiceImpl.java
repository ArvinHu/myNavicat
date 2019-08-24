package com.milla.navicat.service.impl;

import com.milla.navicat.comm.ResponseData;
import com.milla.navicat.mapper.DatabaseConnectionDTOMapperExt;
import com.milla.navicat.pojo.dto.ConnectionDTO;
import com.milla.navicat.pojo.dto.DatabaseConnectionDTO;
import com.milla.navicat.service.IDatabaseConnectionService;
import com.milla.navicat.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private DatabaseConnectionDTOMapperExt connectionMapperExt;

    @Override
    public List<DatabaseConnectionDTO> listConnection() {
        String account = WebUtil.currentAccount();
        return connectionMapperExt.selectConnectionListByAccount(account);
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

package com.milla.navicat.service;

import com.milla.navicat.pojo.dto.DatabaseConnectionDTO;

import java.util.List;

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

    List<DatabaseConnectionDTO> listConnection();

    DatabaseConnectionDTO getConnection(Integer connId);

    boolean testConnection(Integer connId);

    int addConnection(DatabaseConnectionDTO conn);

    int updateConnection(DatabaseConnectionDTO conn);

    int removeConnection(Integer connId);
}

package com.milla.navicat.service;

import com.milla.navicat.config.datasource.dynamic.DataSourceVO;
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

    DatabaseConnectionDTO getConnectionByConnId(Integer connId);

    boolean testConnection(Integer connId);

    DataSourceVO getDataSourceVO(DatabaseConnectionDTO connection);

    int addConnection(DatabaseConnectionDTO conn);

    int updateConnection(DatabaseConnectionDTO conn);

    int removeConnection(Integer connId);
}

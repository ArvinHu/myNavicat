package com.milla.navicat.service.impl;

import com.milla.navicat.config.datasource.dynamic.DataSourceVO;
import com.milla.navicat.config.datasource.dynamic.DynamicDataSource;
import com.milla.navicat.exception.DataSourceException;
import com.milla.navicat.mapper.DatabaseConnectionDTOMapper;
import com.milla.navicat.mapper.DatabaseConnectionDTOMapperExt;
import com.milla.navicat.pojo.dto.DatabaseConnectionDTO;
import com.milla.navicat.service.IDatabaseConnectionService;
import com.milla.navicat.util.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.milla.navicat.constant.SymbolConstant.S_STRIKE_THROUGH;

/**
 * @Package: com.milla.navicat.service.impl
 * @Description: <数据库连接操作类>
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
    private DatabaseConnectionDTOMapper connectionMapper;
    @Autowired
    private DatabaseConnectionDTOMapperExt connectionMapperExt;
    @Autowired
    @Qualifier("dynamicDataSource")
    private DynamicDataSource dynamicDataSource;

    @Override
    public List<DatabaseConnectionDTO> listConnection() {
        String account = WebUtil.currentAccount();
        return connectionMapperExt.selectConnectionListByAccount(account);
    }

    @Override
    public DatabaseConnectionDTO getConnectionByConnId(Integer connId) {
        return checkDatabaseConnectionExist(connId);
    }

    @Override
    public boolean testConnection(Integer connId) {
        DatabaseConnectionDTO connection = checkDatabaseConnectionExist(connId);
        DataSourceVO dataSource = getDataSourceVO(connection);
        return dynamicDataSource.testDatasource(dataSource.getDatabaseType().getDriverClass(), dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
    }

    @Override
    public DataSourceVO getDataSourceVO(DatabaseConnectionDTO connection) {
        DataSourceVO dataSource = new DataSourceVO();
        dataSource.setUsername(connection.getDatabaseUsername());
        dataSource.setPassword(connection.getDatabasePassword());
        dataSource.setDatabaseType(connection.getDatabaseType());
        dataSource.setHost(connection.getDatabaseHost());
        dataSource.setDatabase(connection.getDatabaseDatabase());
        dataSource.setDatasourceId(dataSource.getHost() + S_STRIKE_THROUGH + dataSource.getDatabase() + S_STRIKE_THROUGH + connection.getId());
        return dataSource;
    }

    @Override
    @Transactional
    public int addConnection(DatabaseConnectionDTO conn) {
        String account = WebUtil.currentAccount();
        conn.setAccount(account);
        DatabaseConnectionDTO find = connectionMapperExt.selectConnectionByConnectionDTO(conn);
        if (find != null) {
            throw new DataSourceException("连接数据已存在");
        }
        return connectionMapper.insertSelective(conn);
    }

    @Override
    @Transactional
    public int updateConnection(DatabaseConnectionDTO conn) {
        checkDatabaseConnectionExist(conn.getId());
        return connectionMapper.updateByPrimaryKeySelective(conn);
    }

    @Override
    @Transactional
    public int removeConnection(Integer connId) {
        checkDatabaseConnectionExist(connId);
        return connectionMapper.deleteByPrimaryKey(connId);
    }

    private DatabaseConnectionDTO checkDatabaseConnectionExist(Integer connId) {
        String account = WebUtil.currentAccount();
        DatabaseConnectionDTO find = connectionMapper.selectByPrimaryKey(connId);
        if (find == null || !StringUtils.equals(account, find.getAccount())) {
            throw new DataSourceException("连接数据不存在");
        }
        return find;
    }
}

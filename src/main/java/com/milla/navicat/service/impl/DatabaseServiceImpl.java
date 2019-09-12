package com.milla.navicat.service.impl;

import com.milla.navicat.config.datasource.dynamic.DBContextHolder;
import com.milla.navicat.config.datasource.dynamic.DataSourceVO;
import com.milla.navicat.config.datasource.dynamic.DynamicDataSource;
import com.milla.navicat.exception.DataSourceException;
import com.milla.navicat.pojo.dto.DatabaseConnectionDTO;
import com.milla.navicat.pojo.vo.DatabaseVO;
import com.milla.navicat.service.IDatabaseConnectionService;
import com.milla.navicat.service.IDatabaseService;
import com.milla.navicat.service.IShowService;
import com.milla.navicat.util.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;

import static com.milla.navicat.constant.HeaderParamConstant.C_TABLE_SCHEMA;

/**
 * @Package: com.milla.navicat.service.impl
 * @Description: <数据库操作类>
 * @Author: MILLA
 * @CreateDate: 2019/8/23 9:37
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/23 9:37
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Service
public class DatabaseServiceImpl implements IDatabaseService {
    @Autowired
    @Qualifier("defaultJdbcTemplate")
    private NamedParameterJdbcTemplate template;
    @Autowired
    private IShowService service;

    @Autowired
    private IDatabaseConnectionService connectionService;
    @Autowired
    @Qualifier("dynamicDataSource")
    private DynamicDataSource dynamicDataSource;

    @Override
    public void changeDatabase(Integer connId, String databaseName) {
        Assert.hasText(databaseName, "数据库名称不能为空");
        DBContextHolder.clearDataSource();
        DatabaseConnectionDTO connection = connectionService.getConnectionByConnId(connId);
        connection.setDatabaseDatabase(databaseName);
        DataSourceVO dataSource = connectionService.getDataSourceVO(connection);
        dynamicDataSource.createDataSourceWithCheck(dataSource);
        DBContextHolder.changeDataSource(dataSource.getDatasourceId());
    }

    @Override
    @Transactional
    public int addDatabase(DatabaseVO database) {
        String sql = "CREATE DATABASE `%s` CHARACTER SET '%s' COLLATE '%s'";
        alterDatabase(database, sql);
        return 0;
    }

    private void alterDatabase(DatabaseVO database, String sql) {
        //查询连接是否存在
        connectionService.getConnectionByConnId(database.getConnId());
        //默认字段集
        if (StringUtils.isBlank(database.getCharacterEncoding())) {
            database.setCharacterEncoding("utf8");
        }
        //默认排序规则[前端校验如果字段集选中的话，排序规则是必选的]
        if (StringUtils.isBlank(database.getOrderingRule())) {
            database.setOrderingRule("utf8_general_ci");
        }
        List<String> databases = service.listDatabase(database.getConnId());
        if (database != null && databases.contains(database.getDatabaseName())) {
            throw new DataSourceException("数据库已存在");
        }
        String format = String.format(sql, database.getDatabaseName(), database.getCharacterEncoding(), database.getOrderingRule());
        template.update(format, new HashMap<>());
    }

    @Override
    @Transactional
    public int updateDatabase(DatabaseVO database) {
        String sql = "ALTER DATABASE `%s` CHARACTER SET '%s' COLLATE '%s'";
        alterDatabase(database, sql);
        return 0;
    }

    @Override
    @Transactional
    public int removeDatabase(DatabaseVO database) {
        String sql = "DROP DATABASE IF EXISTS `%s`";
        template.update(String.format(sql, database.getDatabaseName()), new HashMap<>());
        return 0;
    }
}
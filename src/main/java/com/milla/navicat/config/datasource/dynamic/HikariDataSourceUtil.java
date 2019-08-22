package com.milla.navicat.config.datasource.dynamic;

import com.milla.navicat.exception.DataSourceException;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @Package: com.milla.navicat.config.datasource.dynamic
 * @Description: <生成datasource示例>
 * @Author: MILLA
 * @CreateDate: 2019/8/22 9:32
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/22 9:32
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Component
public final class HikariDataSourceUtil {
    //默认配置[静态注入]
    private static HikariConfig config;

    static DataSource initDatasource(DataSourceVO dataSource) {
        return initDatasource(dataSource.getDatasourceId(), dataSource.getDatabaseType().getDriverClass(), dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
    }

    static DataSource initDatasource(String poolName, String driverClassName, String jdbcUrl, String username, String password) {
        if (StringUtils.isAnyBlank(driverClassName, jdbcUrl, username, password)) {
            throw new DataSourceException("数据库连接参数不能为空");
        }
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setPoolName(poolName);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //各参数配置
        defaultConfig(dataSource);
        return dataSource;
    }

    private static void defaultConfig(HikariDataSource dataSource) {
        dataSource.setConnectionTimeout(config.getConnectionTimeout());
        dataSource.setMinimumIdle(config.getMinimumIdle());
        dataSource.setAutoCommit(config.isAutoCommit());
        dataSource.setIdleTimeout(config.getIdleTimeout());
        dataSource.setMaxLifetime(config.getMaxLifetime());
        dataSource.setMaximumPoolSize(config.getMaximumPoolSize());
    }

    static DataSource initDatasource(String driverClassName, String jdbcUrl, String username, String password) {
        return initDatasource("defaultPoolName", driverClassName, jdbcUrl, username, password);
    }

    @Autowired
    public void setConfig(HikariConfig config) {
        this.config = config;
    }
}

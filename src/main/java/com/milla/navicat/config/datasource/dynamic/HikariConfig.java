package com.milla.navicat.config.datasource.dynamic;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Package: com.milla.navicat.config.datasource.dynamic
 * @Description: <数据源配置>
 * @Author: MILLA
 * @CreateDate: 2019/8/22 11:08
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/22 11:08
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource.hikari")
public class HikariConfig {
    private String poolName;
    private int maximumPoolSize;
    private long connectionTimeout;
    private int minimumIdle;
    private long idleTimeout;
    private long maxLifetime;
    private boolean autoCommit;

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getMinimumIdle() {
        return minimumIdle;
    }

    public void setMinimumIdle(int minimumIdle) {
        this.minimumIdle = minimumIdle;
    }

    public long getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(long idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public long getMaxLifetime() {
        return maxLifetime;
    }

    public void setMaxLifetime(long maxLifetime) {
        this.maxLifetime = maxLifetime;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }
}

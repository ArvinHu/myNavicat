package com.milla.navicat.config.datasource.dynamic;

import com.milla.navicat.exception.DataSourceException;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Objects;

/**
 * @Package: com.milla.navicat.config.datasource.dynamic
 * @Description: <动态数据源>
 * @Author: MILLA
 * @CreateDate: 2019/8/21 10:25
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/21 10:25
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    private boolean debug = false;


    private Map<Object, Object> dynamicTargetDataSources;

    private Object dynamicDefaultTargetDataSource;

    @Override
    protected Object determineCurrentLookupKey() {
        String datasource = DBContextHolder.getDataSource();
        if (debug) {
            if (Objects.nonNull(datasource)) {
                Map<Object, Object> dynamicTargetDataSources2 = this.dynamicTargetDataSources;
                if (dynamicTargetDataSources2.containsKey(datasource)) {
                    log.debug("---当前数据源：{}", datasource);
                } else {
                    try {
                        throw new DataSourceException("不存在的数据源：" + datasource, 500);
                    } catch (DataSourceException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                log.debug("---当前数据源：默认数据源---");
            }
        }
        return datasource;
    }


    @Override


    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        this.dynamicTargetDataSources = targetDataSources;
    }

    // 创建数据源
    public boolean createDataSource(String key, String driveClass, String url, String username, String password) {
        try {
            if (!testDatasource(driveClass, url, username, password)) {
                throw new DataSourceException("数据源配置有错误");
            }
            DataSource dataSource = HikariDataSourceUtil.initDatasource(key, driveClass, url, username, password);
            Map<Object, Object> dynamicTargetDataSources2 = this.dynamicTargetDataSources;
            dynamicTargetDataSources2.put(key, dataSource);// 加入map
            setTargetDataSources(dynamicTargetDataSources2);// 将map赋值给父类的TargetDataSources
            super.afterPropertiesSet();// 将TargetDataSources中的连接信息放入resolvedDataSources管理
            log.info("数据源初始化成功：{}", key);
            return true;
        } catch (Exception e) {
            log.error("创建失败,原因：{}", e);
            return false;
        }
    }
//    // 删除数据源
//
//
//    public boolean delDatasources(String datasourceid) {
//        Map<Object, Object> dynamicTargetDataSources2 = this.dynamicTargetDataSources;
//        if (dynamicTargetDataSources2.containsKey(datasourceid)) {
//            Set<HikariDataSource> druidDataSourceInstances = HikariDataSourcePoolMetadata();
//            for (DruidDataSource l : druidDataSourceInstances) {
//                if (datasourceid.equals(l.getName())) {
//                    dynamicTargetDataSources2.remove(datasourceid);
//                    DruidDataSourceStatManager.removeDataSource(l);
//                    setTargetDataSources(dynamicTargetDataSources2);// 将map赋值给父类的TargetDataSources
//                    super.afterPropertiesSet();// 将TargetDataSources中的连接信息放入resolvedDataSources管理
//                    return true;
//                }
//            }
//            return false;
//        } else {
//            return false;
//        }
//    }

    // 测试数据源连接是否有效


    public boolean testDatasource(String driveClass, String url, String username, String password) {
        try {
            Class.forName(driveClass);
            DriverManager.getConnection(url, username, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Specify the default target DataSource, if any.
     * <p>
     * The mapped value can either be a corresponding
     * {@link javax.sql.DataSource} instance or a data source name String (to be
     * resolved via a {@link #setDataSourceLookup DataSourceLookup}).
     * <p>
     * This DataSource will be used as target if none of the keyed
     * {@link #setTargetDataSources targetDataSources} match the
     * {@link #determineCurrentLookupKey()} current lookup key.
     */
    @Override
    public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        this.dynamicDefaultTargetDataSource = defaultTargetDataSource;
    }

    /**
     * @param debug the debug to set
     */


    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * @return the debug
     */


    public boolean isDebug() {
        return debug;
    }

    /**
     * @return the dynamicTargetDataSources
     */


    public Map<Object, Object> getDynamicTargetDataSources() {
        return dynamicTargetDataSources;
    }

    /**
     * @param dynamicTargetDataSources the dynamicTargetDataSources to set
     */


    public void setDynamicTargetDataSources(Map<Object, Object> dynamicTargetDataSources) {
        this.dynamicTargetDataSources = dynamicTargetDataSources;
    }

    /**
     * @return the dynamicDefaultTargetDataSource
     */


    public Object getDynamicDefaultTargetDataSource() {
        return dynamicDefaultTargetDataSource;
    }

    /**
     * @param dynamicDefaultTargetDataSource the dynamicDefaultTargetDataSource to set
     */


    public void setDynamicDefaultTargetDataSource(Object dynamicDefaultTargetDataSource) {
        this.dynamicDefaultTargetDataSource = dynamicDefaultTargetDataSource;
    }


    public void createDataSourceWithCheck(DataSourceVO dataSource) throws Exception {
        String datasourceId = dataSource.getDatasourceId();
        log.info("准备创建数据源" + datasourceId);
        Map<Object, Object> dynamicTargetDataSources2 = this.dynamicTargetDataSources;
        if (dynamicTargetDataSources2.containsKey(datasourceId)) {
            log.info("数据源" + datasourceId + "之前已经创建，准备测试数据源是否正常...");
            HikariDataSource hikariDataSource = (HikariDataSource) dynamicTargetDataSources2.get(datasourceId);
            boolean rightFlag = true;
            Connection connection = null;
            try {
//    log.info(datasourceId+"数据源的概况->当前闲置连接数："+druidDataSource.getPoolingCount());
//    long activeCount = druidDataSource.getActiveCount();
//    log.info(datasourceId+"数据源的概况->当前活动连接数："+activeCount);
//    if(activeCount > 0) {
//     log.info(datasourceId+"数据源的概况->活跃连接堆栈信息："+druidDataSource.getActiveConnectionStackTrace());
//    }
                log.info("准备获取数据库连接...");
                connection = hikariDataSource.getConnection();
                log.info("数据源" + datasourceId + "正常");
            } catch (Exception e) {
                log.error(e.getMessage(), e); //把异常信息打印到日志文件
                rightFlag = false;
                log.info("缓存数据源" + datasourceId + "已失效，准备删除...");
//                if (delDatasources(datasourceId)) {
//                    log.info("缓存数据源删除成功");
//                } else {
//                    log.info("缓存数据源删除失败");
//                }
            } finally {
                if (null != connection) {
                    connection.close();
                }
            }
            if (rightFlag) {
                log.info("不需要重新创建数据源");
                return;
            } else {
                log.info("准备重新创建数据源...");
                createDataSource(dataSource);
                log.info("重新创建数据源完成");
            }
        } else {
            createDataSource(dataSource);
        }

    }

    private void createDataSource(DataSourceVO dataSource) throws Exception {
        String datasourceId = dataSource.getDatasourceId();
        log.info("准备创建数据源:{}", datasourceId);
        DatabaseCategory databaseType = dataSource.getDatabaseType();
        String username = dataSource.getUsername();
        String password = dataSource.getPassword();
//        password = new String(SecurityTools.decrypt(Base64.decode(password)));
        String url = dataSource.getUrl();
        String driveClass = databaseType.getDriverClass();
        if (testDatasource(driveClass, url, username, password)) {
            boolean result = this.createDataSource(datasourceId, driveClass, url, username, password);
            if (!result) {
                throw new DataSourceException("数据源" + datasourceId + "配置正确，但创建失败");
            }
        } else {
            throw new DataSourceException("数据源配置有错误");
        }
    }

}

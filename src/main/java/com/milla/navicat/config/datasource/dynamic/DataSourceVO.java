package com.milla.navicat.config.datasource.dynamic;

/**
 * @Package: com.milla.navicat.config.datasource.dynamic
 * @Description: <数据库实体>
 * @Author: MILLA
 * @CreateDate: 2019/8/21 10:22
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/21 10:22
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class DataSourceVO {

    private String datasourceId;//数据库名称

    private String url;//数据库地址

    private String username;//数据库用户名

    private String password;//数据库密码

    private String characterEncoding;//数据源编码，保证唯一

    private DatabaseCategory databaseType;//数据库类型，支持oracle、mysql、sqlserver2000、sqlserver

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCharacterEncoding() {
        return characterEncoding;
    }

    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    public DatabaseCategory getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseCategory databaseType) {
        this.databaseType = databaseType;
    }
}

package com.milla.navicat.pojo.dto;

public class DatasourceDTO {
    private String datasourceId;

    private String datasourceHost;

    private Integer datasourcePort;

    private String datasourceDatabase;

    private String datasourceUsername;

    private String datasourcePassword;

    private String datasourceCharacterEncoding;

    private String datasourceDatabaseType;

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId == null ? null : datasourceId.trim();
    }

    public String getDatasourceHost() {
        return datasourceHost;
    }

    public void setDatasourceHost(String datasourceHost) {
        this.datasourceHost = datasourceHost == null ? null : datasourceHost.trim();
    }

    public Integer getDatasourcePort() {
        return datasourcePort;
    }

    public void setDatasourcePort(Integer datasourcePort) {
        this.datasourcePort = datasourcePort;
    }

    public String getDatasourceDatabase() {
        return datasourceDatabase;
    }

    public void setDatasourceDatabase(String datasourceDatabase) {
        this.datasourceDatabase = datasourceDatabase == null ? null : datasourceDatabase.trim();
    }

    public String getDatasourceUsername() {
        return datasourceUsername;
    }

    public void setDatasourceUsername(String datasourceUsername) {
        this.datasourceUsername = datasourceUsername == null ? null : datasourceUsername.trim();
    }

    public String getDatasourcePassword() {
        return datasourcePassword;
    }

    public void setDatasourcePassword(String datasourcePassword) {
        this.datasourcePassword = datasourcePassword == null ? null : datasourcePassword.trim();
    }

    public String getDatasourceCharacterEncoding() {
        return datasourceCharacterEncoding;
    }

    public void setDatasourceCharacterEncoding(String datasourceCharacterEncoding) {
        this.datasourceCharacterEncoding = datasourceCharacterEncoding == null ? null : datasourceCharacterEncoding.trim();
    }

    public String getDatasourceDatabaseType() {
        return datasourceDatabaseType;
    }

    public void setDatasourceDatabaseType(String datasourceDatabaseType) {
        this.datasourceDatabaseType = datasourceDatabaseType == null ? null : datasourceDatabaseType.trim();
    }
}
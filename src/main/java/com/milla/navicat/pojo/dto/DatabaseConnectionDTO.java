package com.milla.navicat.pojo.dto;

public class DatabaseConnectionDTO {
    private Integer id;

    private String account;

    private String databaseHost;

    private Integer databasePort;

    private String databaseDatabase;

    private String databaseUsername;

    private String databasePassword;

    private String databaseCharacterEncoding;

    private String databaseType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getDatabaseHost() {
        return databaseHost;
    }

    public void setDatabaseHost(String databaseHost) {
        this.databaseHost = databaseHost == null ? null : databaseHost.trim();
    }

    public Integer getDatabasePort() {
        return databasePort;
    }

    public void setDatabasePort(Integer databasePort) {
        this.databasePort = databasePort;
    }

    public String getDatabaseDatabase() {
        return databaseDatabase;
    }

    public void setDatabaseDatabase(String databaseDatabase) {
        this.databaseDatabase = databaseDatabase == null ? null : databaseDatabase.trim();
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername == null ? null : databaseUsername.trim();
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword == null ? null : databasePassword.trim();
    }

    public String getDatabaseCharacterEncoding() {
        return databaseCharacterEncoding;
    }

    public void setDatabaseCharacterEncoding(String databaseCharacterEncoding) {
        this.databaseCharacterEncoding = databaseCharacterEncoding == null ? null : databaseCharacterEncoding.trim();
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType == null ? null : databaseType.trim();
    }
}
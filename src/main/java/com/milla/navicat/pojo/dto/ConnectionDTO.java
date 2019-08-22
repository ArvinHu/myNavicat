package com.milla.navicat.pojo.dto;

/**
 * @Package: com.milla.navicat.pojo.dto
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/15 18:39
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/15 18:39
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class ConnectionDTO {

    //主键
    private Integer connId;
    //连接名称
    private String connName;
    //项目
    private String project;
    //主机
    private String host;
    //端口
    private int port;
    //用户名
    private String username;
    //用户密码
    private String password;
    //是否保存密码
    private boolean savedPassword;

    public Integer getConnId() {
        return connId;
    }

    public void setConnId(Integer connId) {
        this.connId = connId;
    }

    public String getConnName() {
        return connName;
    }

    public void setConnName(String connName) {
        this.connName = connName;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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

    public boolean isSavedPassword() {
        return savedPassword;
    }

    public void setSavedPassword(boolean savedPassword) {
        this.savedPassword = savedPassword;
    }

    @Override
    public String toString() {
        return "ConnectionDTO{" +
                "connId=" + connId +
                ", connName='" + connName + '\'' +
                ", project='" + project + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", savedPassword=" + savedPassword +
                '}';
    }
}

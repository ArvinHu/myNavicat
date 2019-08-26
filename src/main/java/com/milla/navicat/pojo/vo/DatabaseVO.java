package com.milla.navicat.pojo.vo;

import javax.validation.constraints.NotNull;

/**
 * @Package: com.milla.navicat.pojo.vo
 * @Description: <数据库>
 * @Author: MILLA
 * @CreateDate: 2019/8/26 16:25
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/26 16:25
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class DatabaseVO {
    //数据库所属的连接
    @NotNull
    private Integer connId;
    //数据库名称
    @NotNull
    private String databaseName;
    //字符集[字符编码]
    private String characterEncoding;
    //排序规则
    private String orderingRule;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getCharacterEncoding() {
        return characterEncoding;
    }

    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    public String getOrderingRule() {
        return orderingRule;
    }

    public void setOrderingRule(String orderingRule) {
        this.orderingRule = orderingRule;
    }

    public Integer getConnId() {
        return connId;
    }

    public void setConnId(Integer connId) {
        this.connId = connId;
    }

    @Override
    public String toString() {
        return "DatabaseVO{" +
                "connId=" + connId +
                ", databaseName='" + databaseName + '\'' +
                ", characterEncoding='" + characterEncoding + '\'' +
                ", orderingRule='" + orderingRule + '\'' +
                '}';
    }
}

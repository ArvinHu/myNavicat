package com.milla.navicat.pojo.bo;

/**
 * @Package: com.milla.navicat.pojo.bo
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/10/17 19:17
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/10/17 19:17
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class DuplicateTableBo {
    //复制表格类型 1：结构和数据 0：仅结构
    private int category;
    //表格名称
    private String tableName;

    public DuplicateTableBo() {
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}

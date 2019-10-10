package com.milla.navicat.comm;

import java.io.Serializable;

public class OrderBy implements Serializable {

    /**
     * 排序字段
     */
    private String column;

    /**
     * 排序 asc desc
     */
    private String order;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}

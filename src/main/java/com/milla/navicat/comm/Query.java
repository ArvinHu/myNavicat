package com.milla.navicat.comm;

import java.io.Serializable;

public class Query implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Integer pageSize = 500;

    protected Integer pageNum = 1;

    protected OrderBy orderBy;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public OrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}

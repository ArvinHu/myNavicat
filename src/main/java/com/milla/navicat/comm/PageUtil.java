package com.milla.navicat.comm;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.pagehelper.Page;

import java.io.Serializable;

/**
 * @Package: com.aimsphm.common
 * @Description: <分页实体>
 * @Author: MILLA
 * @CreateDate: 2018/8/17 17:58
 * @UpdateUser: MILLA
 * @UpdateDate: 2018/8/17 17:58
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class PageUtil<T> implements Serializable {
    private static final long serialVersionUID = 1013L;
    @JSONField(ordinal = 1)
    private Long total;
    @JSONField(ordinal = 2)
    private Integer pages;
    @JSONField(ordinal = 3)
    private Integer pageNum;
    @JSONField(ordinal = 4)
    private T list;

    public PageUtil(Page page, T list) {
        this.total = page.getTotal();
        this.pages = page.getPages();
        this.pageNum = page.getPageNum();
        this.list = list;
    }


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    public PageUtil(Long total, Integer pages, Integer pageNum, T list) {
        this.total = total;
        this.pages = pages;
        this.pageNum = pageNum;
        this.list = list;
    }

    public static <T> PageUtil page(Long total, Integer pages, Integer pageNum, T list) {
        return new PageUtil(total, pages, pageNum, list);
    }
}

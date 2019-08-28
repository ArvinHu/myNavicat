package com.milla.navicat.pojo.vo;

import java.util.List;

/**
 * @Package: com.milla.navicat.pojo.vo
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/28 17:27
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/28 17:27
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class TableVO {
    //表格名称
    private String tableName;
    //表字段列表
    private List<TableColumnVO> columns;
    //表索引列表
    private List<TableIndexVO> indexVOS;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<TableColumnVO> getColumns() {
        return columns;
    }

    public void setColumns(List<TableColumnVO> columns) {
        this.columns = columns;
    }

    public List<TableIndexVO> getIndexVOS() {
        return indexVOS;
    }

    public void setIndexVOS(List<TableIndexVO> indexVOS) {
        this.indexVOS = indexVOS;
    }
}

package com.milla.navicat.pojo.vo;

import com.milla.navicat.pojo.enums.EnumTableIndexCategory;
import com.milla.navicat.pojo.enums.EnumTableIndexFunction;

/**
 * @Package: com.milla.navicat.pojo.vo
 * @Description: <表索引实体>
 * @Author: MILLA
 * @CreateDate: 2019/8/28 17:32
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/28 17:32
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class TableIndexVO {
    //所属表格
    private String table;
    //索引名称
    private String keyName;
    //字段名称
    private String columnName;
    //排序规则
    private String collation;
    //基数
    private int cardinality;
    //索引注释
    private String indexComment;
    //索引方法
    private EnumTableIndexFunction indexFunction;
    //索引种类
    private EnumTableIndexCategory indexCategory;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getCollation() {
        return collation;
    }

    public void setCollation(String collation) {
        this.collation = collation;
    }

    public int getCardinality() {
        return cardinality;
    }

    public void setCardinality(int cardinality) {
        this.cardinality = cardinality;
    }

    public String getIndexComment() {
        return indexComment;
    }

    public void setIndexComment(String indexComment) {
        this.indexComment = indexComment;
    }

    public EnumTableIndexFunction getIndexFunction() {
        return indexFunction;
    }

    public void setIndexFunction(EnumTableIndexFunction indexFunction) {
        this.indexFunction = indexFunction;
    }

    public EnumTableIndexCategory getIndexCategory() {
        return indexCategory;
    }

    public void setIndexCategory(EnumTableIndexCategory indexCategory) {
        this.indexCategory = indexCategory;
    }
}

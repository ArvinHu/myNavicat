package com.milla.navicat.pojo.dto;

/**
 * @Package: com.milla.navicat.pojo.dto
 * @Description: <表索引实体>
 * @Author: MILLA
 * @CreateDate: 2019/8/28 17:32
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/28 17:32
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class TableIndexDTO {
    //所属表格
    private String table;
    //是否唯一
    private boolean nonUnique;
    //索引名称
    private String keyName;
    //字段名称
    private String columnName;
    //排序规则
    private String collation;
    //基数
    private int cardinality;
    //索引方法
    private String indexType;
    //索引注释
    private String indexComment;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public boolean isNonUnique() {
        return nonUnique;
    }

    public void setNonUnique(boolean nonUnique) {
        this.nonUnique = nonUnique;
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

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getIndexComment() {
        return indexComment;
    }

    public void setIndexComment(String indexComment) {
        this.indexComment = indexComment;
    }

}

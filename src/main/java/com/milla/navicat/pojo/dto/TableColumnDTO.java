package com.milla.navicat.pojo.dto;

/**
 * @Package: com.milla.navicat.pojo.dto
 * @Description: <表字段实体>
 * @Author: MILLA
 * @CreateDate: 2019/8/28 16:19
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/28 16:19
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class TableColumnDTO {
    //---------------数据库表中的字段-----------------------
    //字段名称
    private String columnName;
    //字段默认值
    private String columnDefault;
    //是否为空 yes为空 no 不为空
    private String isNullable;
    //字段类型
    private String dataType;
    //字段类型包含(10,2)int(11) unsigned zerofill
    private String columnType;
    //字符集
    private String characterSetName;
    //字符集排序规则
    private String collationName;
    //主键，索引标识
    private String columnKey;
    //扩展说明(主键自增)auto_increment
    private String extra;
    //字段注释
    private String columnComment;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
}


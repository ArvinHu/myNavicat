package com.milla.navicat.pojo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.milla.navicat.pojo.enums.EnumTableColumn;

/**
 * @Package: com.milla.navicat.pojo.vo
 * @Description: <表字段实体>
 * @Author: MILLA
 * @CreateDate: 2019/8/28 16:19
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/28 16:19
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class TableColumnVO {
    //---------------数据库表中的字段-----------------------
    //字段名称
    private String columnName;
    //字段默认值
    private String columnDefault;
    //字段类型
    private String dataType;
    //字符集
    private String characterSetName;
    //字符集排序规则
    private String collationName;
    //字段注释
    private String columnComment;

    //----------------业务中需要使用的字段-----------------------
    //是否为空 数据库中yes为空 no 不为空 默认为空
    private boolean isNullable = false;
    //是否无符号 默认有符号
    private boolean unsigned = true;
    //是否用0填充 默认不用0填充
    private boolean zerofill = false;
    //是否自增长 默认不自增长
    private boolean autoIncrement = false;
    //是否是主键 默认不是主键
    private boolean primaryKey = false;
    //是否是二进制
    private boolean isBinary = false;
    //显示宽度
    private long displayWidth;
    //小数点
    private int scale;
    //字段类型
    @JSONField(serialize = false)//设置字段不显示
    private EnumTableColumn columnCategory;

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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public boolean isUnsigned() {
        return unsigned;
    }

    public void setUnsigned(boolean unsigned) {
        this.unsigned = unsigned;
    }

    public boolean isZerofill() {
        return zerofill;
    }

    public void setZerofill(boolean zerofill) {
        this.zerofill = zerofill;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public long getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(long displayWidth) {
        this.displayWidth = displayWidth;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public EnumTableColumn getColumnCategory() {
        return columnCategory;
    }

    public void setColumnCategory(EnumTableColumn columnCategory) {
        this.columnCategory = columnCategory;
    }

    public boolean isBinary() {
        return isBinary;
    }

    public void setBinary(boolean binary) {
        isBinary = binary;
    }
}

package com.milla.navicat.pojo.vo;

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
    //是否为空 yes为空 no 不为空
    private String isNullable;
    //字段类型
    private String dataType;
    //字段类型包含(10,2)
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

    //----------------业务中需要使用的字段-----------------------
    //是否有符号
    private boolean signed;
    //是否用0填充
    private boolean zerofill;
    //是否自增长
    private boolean autoIncrement;
    //是否是主键
    private boolean primaryKey;
    //显示宽度
    private long displayWidth;
    //小数点
    private int scale;
    //字段类型
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

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public boolean isZerofill() {
        return zerofill;
    }

    public void setZerofill(boolean zerofill) {
        this.zerofill = zerofill;
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
}
//    SELECT
//    sys.COLUMN_NAME,
//    sys.COLUMN_DEFAULT,
//    sys.IS_NULLABLE,
//    sys.DATA_TYPE,
//    sys.COLUMN_TYPE,
//    sys.CHARACTER_SET_NAME,
//    sys.COLLATION_NAME,
//    sys.COLUMN_KEY,
//    sys.EXTRA,
//    sys.COLUMN_COMMENT
//            FROM
//    information_schema.`COLUMNS` sys
//            WHERE
//    sys.TABLE_SCHEMA = 'crrc_dev'
//    AND sys.TABLE_NAME = 'failure_info'


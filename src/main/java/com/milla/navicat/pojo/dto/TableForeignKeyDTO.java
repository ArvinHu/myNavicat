package com.milla.navicat.pojo.dto;

import com.milla.navicat.pojo.enums.EnumTableForeignKeyRule;

/**
 * @Package: com.milla.navicat.pojo.vo
 * @Description: <外键实体>
 * @Author: MILLA
 * @CreateDate: 2019/9/2 17:58
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/9/2 17:58
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class TableForeignKeyDTO {
    //---------------INFORMATION_SCHEMA.KEY_COLUMN_USAGE------------------
    //外键种类
    private String constraintCatalog;//'def',
    //外键所属的模式
    private String constraintSchema;//'tcc_order',
    //外键名称
    private String constraintName;//'course_copy1_ibfk_1',
    //表格种类
    private String tableCatalog;//'def',
    //当前表格的模式
    private String tableSchema;//'tcc_order',
    //表格
    private String tableName;//'course_copy1',
    //字段
    private String columnName;//'st_no',
    private String ordinalPosition;//1,
    private String positionInUniqueConstraint;//1,
    //参考模式
    private String referencedTableSchema;//'tcc_order',
    //参考表
    private String referencedTableName;//'student',
    //参考字段
    private String referencedColumnName;//'st_no',
    //---------------INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS---------------
    //更新规则
    private EnumTableForeignKeyRule updateRule;//'cascade',
    //删除规则
    private EnumTableForeignKeyRule deleteRule;//'restrict',

    private String uniqueConstraintCatalog;//'def',
    private String uniqueConstraintSchema;//'tcc_order',
    private String uniqueConstraintName;//'primary',
    private String matchOption;//'none',

    public String getConstraintCatalog() {
        return constraintCatalog;
    }

    public void setConstraintCatalog(String constraintCatalog) {
        this.constraintCatalog = constraintCatalog;
    }

    public String getConstraintSchema() {
        return constraintSchema;
    }

    public void setConstraintSchema(String constraintSchema) {
        this.constraintSchema = constraintSchema;
    }

    public String getConstraintName() {
        return constraintName;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
    }

    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(String ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getPositionInUniqueConstraint() {
        return positionInUniqueConstraint;
    }

    public void setPositionInUniqueConstraint(String positionInUniqueConstraint) {
        this.positionInUniqueConstraint = positionInUniqueConstraint;
    }

    public String getReferencedTableSchema() {
        return referencedTableSchema;
    }

    public void setReferencedTableSchema(String referencedTableSchema) {
        this.referencedTableSchema = referencedTableSchema;
    }

    public String getReferencedTableName() {
        return referencedTableName;
    }

    public void setReferencedTableName(String referencedTableName) {
        this.referencedTableName = referencedTableName;
    }

    public String getReferencedColumnName() {
        return referencedColumnName;
    }

    public void setReferencedColumnName(String referencedColumnName) {
        this.referencedColumnName = referencedColumnName;
    }

    public EnumTableForeignKeyRule getUpdateRule() {
        return updateRule;
    }

    public void setUpdateRule(EnumTableForeignKeyRule updateRule) {
        this.updateRule = updateRule;
    }

    public EnumTableForeignKeyRule getDeleteRule() {
        return deleteRule;
    }

    public void setDeleteRule(EnumTableForeignKeyRule deleteRule) {
        this.deleteRule = deleteRule;
    }

    public String getUniqueConstraintCatalog() {
        return uniqueConstraintCatalog;
    }

    public void setUniqueConstraintCatalog(String uniqueConstraintCatalog) {
        this.uniqueConstraintCatalog = uniqueConstraintCatalog;
    }

    public String getUniqueConstraintSchema() {
        return uniqueConstraintSchema;
    }

    public void setUniqueConstraintSchema(String uniqueConstraintSchema) {
        this.uniqueConstraintSchema = uniqueConstraintSchema;
    }

    public String getUniqueConstraintName() {
        return uniqueConstraintName;
    }

    public void setUniqueConstraintName(String uniqueConstraintName) {
        this.uniqueConstraintName = uniqueConstraintName;
    }

    public String getMatchOption() {
        return matchOption;
    }

    public void setMatchOption(String matchOption) {
        this.matchOption = matchOption;
    }
}

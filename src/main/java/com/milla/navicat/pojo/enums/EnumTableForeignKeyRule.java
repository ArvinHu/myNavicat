package com.milla.navicat.pojo.enums;

/**
 * @Package: com.milla.navicat.pojo.enums
 * @Description: <外键规则>
 * @Author: MILLA
 * @CreateDate: 2019/8/31 9:40
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/31 9:40
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public enum EnumTableForeignKeyRule {
    //级联
    CASCADE("CASCADE"),
    //不做任何操作
    NO_ACTION("NO ACTION"),
    //限制
    RESTRICT("RESTRICT"),
    //设置为空
    SET_NULL("SET NULL");
    private String code;

    EnumTableForeignKeyRule(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static void main(String[] args) {
        EnumTableForeignKeyRule enumTableForeignKeyRule = EnumTableForeignKeyRule.valueOf("NO ACTION");
    }
}

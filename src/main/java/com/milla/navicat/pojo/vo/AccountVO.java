package com.milla.navicat.pojo.vo;

import io.swagger.annotations.ApiParam;

/**
 * @Package: com.milla.navicat.pojo.dto
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/16 10:44
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/16 10:44
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class AccountVO {
    //用户名
    @ApiParam(defaultValue = "coco1013")
    private String account;
    //密码
    @ApiParam(defaultValue = "111111")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}

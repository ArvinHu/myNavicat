package com.milla.navicat.pojo.dto;

import com.milla.navicat.pojo.vo.AccountVO;

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
public class AccountDTO extends AccountVO {
    //加盐
    private String salt;


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}

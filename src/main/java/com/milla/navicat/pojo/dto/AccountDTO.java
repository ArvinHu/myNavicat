package com.milla.navicat.pojo.dto;

import com.milla.navicat.pojo.vo.AccountVO;

public class AccountDTO extends AccountVO {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}
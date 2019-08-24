package com.milla.navicat.exception;

/**
 * @Package: com.milla.navicat.exception
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/24 9:40
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/24 9:40
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class AccountException extends RuntimeException {
    public AccountException(String code) {
        super(code);
    }
}

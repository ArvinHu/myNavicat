package com.milla.navicat.service;

import com.milla.navicat.pojo.vo.AccountVO;

/**
 * @Package: com.milla.navicat.service
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/16 10:37
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/16 10:37
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public interface IAccountService {
    String login(AccountVO account);
}

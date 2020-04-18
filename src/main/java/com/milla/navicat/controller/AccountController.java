package com.milla.navicat.controller;

import com.milla.navicat.pojo.vo.AccountVO;
import com.milla.navicat.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Package: com.milla.navicat.controller
 * @Description: <显示数据库、表、视图>
 * @Author: MILLA
 * @CreateDate: 2019/8/15 19:00
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/15 19:00
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AccountController {
    @Autowired
    private IAccountService service;

    @PostMapping(value = "/login")
    public String login(@RequestBody AccountVO account, HttpSession session) {
        log.debug("controller-sessionId：{}", session.getId());
        return service.login(account);
    }
}

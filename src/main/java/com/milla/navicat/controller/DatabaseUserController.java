package com.milla.navicat.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DatabaseUserController {

}

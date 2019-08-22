package com.milla.navicat.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Package: com.milla.navicat.util.token
 * @Description: <session、request等工具类>
 * @Author: MILLA
 * @CreateDate: 2019/8/20 13:56
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/20 13:56
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public final class WebUtil extends WebUtils {
    private static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static HttpSession getSession() {
        return getRequestAttributes().getRequest().getSession();
    }
}

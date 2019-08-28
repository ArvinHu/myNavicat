package com.milla.navicat.util;

import com.milla.navicat.exception.AccountException;
import com.milla.navicat.pojo.vo.TokenVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.milla.navicat.constant.Constant.EX_NO_TOKEN_EXCEPTION;
import static com.milla.navicat.constant.HeaderParamConstant.C_TABLE_SCHEMA;
import static com.milla.navicat.constant.HeaderParamConstant.C_TOKEN;
import static com.milla.navicat.constant.SymbolConstant.S_STRIKE_THROUGH;

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

    public static String currentAccount() {
        TokenVO tokenServer = (TokenVO) getSession().getAttribute(C_TOKEN);
        if (tokenServer == null || StringUtils.isBlank(tokenServer.getAccount())) {
            throw new AccountException(EX_NO_TOKEN_EXCEPTION);
        }
        return tokenServer.getAccount();
    }

    public static String currentTableSchema() {
        String tableSchema = (String) getSession().getAttribute(C_TABLE_SCHEMA);
        if (StringUtils.isNotBlank(tableSchema)) {
            String[] split = tableSchema.split(S_STRIKE_THROUGH);
            if (split.length == 3) {
                return split[1];
            }
        }
        return null;
    }
}

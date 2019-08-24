package com.milla.navicat.interceptor;

import com.milla.navicat.exception.AccountException;
import com.milla.navicat.pojo.vo.TokenVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import static com.milla.navicat.constant.Constant.C_TOKEN;
import static com.milla.navicat.constant.Constant.EX_NO_TOKEN_EXCEPTION;

/**
 * @Package: com.milla.navicat.interceptor
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/16 17:07
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/16 17:07
 * @UpdateRemark: <>
 * @Version: 1.0
 */
//@Component
@Slf4j
public class TokenHandlerInterceptor implements HandlerInterceptor {
    private static final long SETTINGS_DATE_LINE_DAYS = 30;
    private static final long SETTINGS_MIN_FLUSH_MS = 12 * 60 * 60 * 1000;
    private static final String SETTINGS_SYSTEM_DEFAULT_ZONE = "+8";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String tokenClient = request.getHeader(C_TOKEN);
        TokenVO tokenServer = (TokenVO) request.getSession().getAttribute(C_TOKEN);
        //客户端token和服务端token一致且没有过期时放行
        if (Objects.nonNull(tokenServer) && Objects.nonNull(tokenServer.getToken()) && tokenServer.getToken().length() > 0 && tokenServer.getToken().equals(tokenClient)) {
            boolean isSuccess = refreshToken(tokenServer);
            //token已经过期了
            if (!isSuccess) {
                throw new AccountException(EX_NO_TOKEN_EXCEPTION);
            }
            return true;
        }
        throw new AccountException(EX_NO_TOKEN_EXCEPTION);
    }

    //有token快到期了重新分配token的时间
    private boolean refreshToken(TokenVO tokenServer) {
        long expiration = tokenServer.getExpiration();
        long currentTimeMillis = System.currentTimeMillis();
        //过期了
        if (currentTimeMillis > expiration) {
            return false;
        }
        //如果token期限已经不足12个小时，刷新token期限为1个月
        if (expiration - currentTimeMillis < SETTINGS_MIN_FLUSH_MS) {
            LocalDateTime localDateTime = LocalDateTime.now().plusDays(SETTINGS_DATE_LINE_DAYS);
            tokenServer.setExpiration(localDateTime.toInstant(ZoneOffset.of(SETTINGS_SYSTEM_DEFAULT_ZONE)).toEpochMilli());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}

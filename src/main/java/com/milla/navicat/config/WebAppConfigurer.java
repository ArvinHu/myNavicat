package com.milla.navicat.config;

import com.milla.navicat.interceptor.TokenHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Package: com.milla.navicat.config
 * @Description: <mvc的一些配置>
 * @Author: MILLA
 * @CreateDate: 2019/8/16 17:19
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/16 17:19
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
    //    @Autowired
    private TokenHandlerInterceptor interceptor;

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(interceptor).addPathPatterns("/**");
//        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns("/account/login", "/account/no-token", "/webjars/**", "swagger-ui.html");
    }

}

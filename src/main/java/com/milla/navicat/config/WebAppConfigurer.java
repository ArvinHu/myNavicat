package com.milla.navicat.config;

import com.google.common.collect.Lists;
import com.milla.navicat.interceptor.TokenHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

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
    @Autowired
    private TokenHandlerInterceptor interceptor;

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(interceptor).addPathPatterns("/**");
        ArrayList<String> list = Lists.newArrayList();
        list.add("/swagger-resources/**");
        list.add("/swagger-ui.html");
        list.add("/swagger-resources");
        list.add("/v2/api-docs");
        list.add("/webjars/**");
        list.add("/account/login");
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(list);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*");
    }
}

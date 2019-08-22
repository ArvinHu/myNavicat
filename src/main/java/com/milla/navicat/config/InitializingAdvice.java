package com.milla.navicat.config;

import com.milla.navicat.comm.ResponseData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.milla.navicat.config
 * @Description: <增强controller层返回值>
 * @Author: MILLA
 * @CreateDate: 2019/8/20 10:33
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/20 10:33
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Configuration
public class InitializingAdvice implements InitializingBean {
    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList(returnValueHandlers);
        this.decorateHandlers(handlers);
        adapter.setReturnValueHandlers(handlers);
    }

    //使用自定义的返回值控制类
    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        for (HandlerMethodReturnValueHandler handler : handlers) {
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                ControllerHandlerMethodReturnValueHandler decorator = new ControllerHandlerMethodReturnValueHandler((RequestResponseBodyMethodProcessor) handler);
                int index = handlers.indexOf(handler);
                handlers.set(index, decorator);
                break;
            }
        }
    }

    private class ControllerHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

        private HandlerMethodReturnValueHandler handler;

        ControllerHandlerMethodReturnValueHandler(RequestResponseBodyMethodProcessor handler) {
            this.handler = handler;
        }

        @Override
        public boolean supportsReturnType(MethodParameter returnType) {
            return true;
        }

        @Override
        public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
            //如果已经封装了结构体就直接放行
            if (returnValue instanceof ResponseData) {
                handler.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
                return;
            }
            ResponseData success = ResponseData.success(returnValue);
            handler.handleReturnValue(success, returnType, mavContainer, webRequest);
        }
    }
}

package com.milla.navicat.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.milla.navicat.processor
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/16 15:10
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/16 15:10
 * @UpdateRemark: <>
 * @Version: 1.0
 */
//@Component
@Slf4j
public class ControllerBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RestController.class) || bean.getClass().isAnnotationPresent(Controller.class)) {
            System.out.println("结果" + beanName);
            log.info("类名称：{},{}", beanName, bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RestController.class) || bean.getClass().isAnnotationPresent(Controller.class)) {
            System.out.println("结果" + beanName);
            log.info("类名称：{},{}", beanName, bean);
        }
        return bean;
    }

}

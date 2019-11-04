package com.milla.navicat.spring.study.service.impl;

import com.google.common.collect.Maps;
import com.milla.navicat.spring.study.event.OrderServiceEvent;
import com.milla.navicat.spring.study.service.CalculateAmountService;
import com.milla.navicat.spring.study.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Package: com.milla.navicat.spring.study.service.impl
 * @Description: <六大设计原则>
 * @Author: MILLA
 * @CreateDate: 2019/11/1 16:32
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/11/1 16:32
 * @UpdateRemark: <>
 * @Version: 1.0
 * @1、单一职责原则 一个类值负责一个功能功能领域中的响应职责。做到高内聚、低耦合
 * @2、开闭原则 对扩展开放，对修改关闭。不修改原有代码的情况下扩展
 * @3、里氏交换原则 所有引用基类(父类)的地方必须能透明地使用其子类对象
 * @4、依赖倒转原则 抽象不应该依赖于细节，细节应当依赖于抽象
 * @5、接口隔离原则 接口拆分。当一个接口太大时，需要将它分割成一些更细小的接口
 * @6、迪米特法则 减少依赖。一个如那件的实体应该尽可能少地与其他实体发生相互作用
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    //自动注入一个map(key是托管给Spring管理的引用名称，value为真实对象)
    @Autowired
    private Map<String, CalculateAmountService> calculateAmountServiceMap;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public double getPaymentAmount(String userType, double amount) {
        log.debug("所有的折扣对象：{}", calculateAmountServiceMap);
        //根据用户的类型从map对象中找到对应的对象进行金额的计算
        CalculateAmountService service = calculateAmountServiceMap.get(userType);
        if (Objects.isNull(service)) {
            return amount;
        }
        return service.getPaymentAmountByDisCount(amount);
    }

    @Override
    public boolean payment(String userType, double amount, double discountAmount) {
        double paymentAmount = this.getPaymentAmount(userType, amount);
        Assert.isTrue(discountAmount != paymentAmount, "折扣计算有误");
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("account", "唯一账户信息");//用以获取业务中需要的信息，比如电话号、邮箱、订单信息等等
        map.put("userType", userType);
        map.put("payAmount", discountAmount);
        //支付操作;支付成功,做推送业务
        OrderServiceEvent event = new OrderServiceEvent(map);
        applicationContext.publishEvent(event);//发布事件
        return true;
    }

}

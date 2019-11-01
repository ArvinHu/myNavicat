package com.milla.navicat.spring.study.service.impl;

import com.milla.navicat.spring.study.service.CalculateAmountService;
import com.milla.navicat.spring.study.service.SaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SaleServiceImpl implements SaleService {
    @Autowired
    private Map<String, CalculateAmountService> calculateAmountServiceMap;

    @Override
    public double sale(String userType, double fee) {
        log.info("所有的折扣对象：{}", calculateAmountServiceMap);
        CalculateAmountService service = calculateAmountServiceMap.get(userType);
        if (Objects.isNull(service)) {
            return fee;
        }
        return service.saleByDisCount(fee);
    }
}

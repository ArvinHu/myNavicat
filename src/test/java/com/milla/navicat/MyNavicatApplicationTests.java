package com.milla.navicat;

import com.milla.navicat.spring.study.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MyNavicatApplicationTests {
    @Autowired
    ApplicationContext context;

    @Test
    public void contextLoads() {
        Long i = (long) 32;
    }

    @Autowired
    private OrderService saleService;

    @Test
    public void testDiscountByUserType() {
        log.info("youke  类型的折扣：{}", saleService.getPaymentAmount("youke", 100));
        log.info("normal 类型的折扣：{}", saleService.getPaymentAmount("normal", 100));
        log.info("vip 类型的折扣：{}", saleService.getPaymentAmount("vip", 100));
        log.info("svip 类型的折扣：{}", saleService.getPaymentAmount("svip", 100));
        log.info("gold 类型的折扣：{}", saleService.getPaymentAmount("gold", 100));
    }

}

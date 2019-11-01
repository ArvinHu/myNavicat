package com.milla.navicat;

import com.milla.navicat.event.TableEvent;
import com.milla.navicat.spring.study.service.SaleService;
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
        TableEvent even = new TableEvent("send email bs");
        context.publishEvent(even);
    }

    @Autowired
    private SaleService saleService;

    @Test
    public void testDiscountByUserType() {
        log.info("youke  类型的折扣：{}", saleService.sale("youke", 100));
        log.info("normal 类型的折扣：{}", saleService.sale("normal", 100));
        log.info("vip 类型的折扣：{}", saleService.sale("vip", 100));
        log.info("svip 类型的折扣：{}", saleService.sale("svip", 100));
        log.info("gold 类型的折扣：{}", saleService.sale("gold", 100));
    }

}

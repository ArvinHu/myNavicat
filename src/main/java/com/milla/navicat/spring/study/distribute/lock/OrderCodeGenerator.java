package com.milla.navicat.spring.study.distribute.lock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Package: com.milla.navicat.spring.study.distribute.lock
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2020/3/13 15:28
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/3/13 15:28
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class OrderCodeGenerator {
    private int i = 0;

    public String getOrderCode() {
        LocalDateTime rightNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String orderId = rightNow.format(formatter);
        return orderId + " - " + ++i;//简单的时间加整型自增[整型自增可用作是否唯一的依据]
    }
}

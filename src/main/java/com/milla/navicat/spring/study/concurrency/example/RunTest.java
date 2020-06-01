package com.milla.navicat.spring.study.concurrency.example;

/**
 * @Package: com.milla.navicat.spring.study.concurrency.example
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2020/3/27 17:26
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/3/27 17:26
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class RunTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName() + "123456"), "hehe");
        thread.run();
    }
}

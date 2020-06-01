package com.milla.navicat.spring.study.io;

import org.apache.commons.net.SocketClient;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Package: com.milla.navicat.spring.study.io
 * @Description: <阻塞式网络编程>
 * @Author: MILLA
 * @CreateDate: 2020/4/14 15:10
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/4/14 15:10
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class IOTests {
    public static void main(String[] args) throws IOException, InterruptedException {
//        requestServer();
        test();
    }

    private static void test() throws InterruptedException {
        int threadNumber = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNumber);
        for (int i = 0; i < threadNumber; i++) {
            Thread.sleep(1000L);
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + " - " + Thread.currentThread().getName() + " - ");
            }, "name-" + i).start();

        }
    }

    private static void requestServer() {
        int threadNumber = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNumber);
        ExecutorService pool = Executors.newCachedThreadPool();
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < threadNumber; i++) {
            pool.submit(() -> {
                        try {
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                        restTemplate.getForEntity("http://localhost:8080", String.class);
                    }
            );

        }
        pool.shutdown();
    }
}

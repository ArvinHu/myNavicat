package com.milla.navicat.spring.study.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
public class BIODemo {
    public static void main(String[] args) throws IOException, InterruptedException {
//        startServer();
    }

    private static void startServer() throws IOException {
        ExecutorService pool = Executors.newCachedThreadPool();
        ServerSocket socket = new ServerSocket(8080);
        System.out.println("服务已经启动了....");
        while (true) {
            Socket accept = socket.accept();//阻塞式的
            pool.submit(() -> {
                byte[] bytes = new byte[1024];
                InputStream inputStream = null;
                try {
                    inputStream = accept.getInputStream();//读取数据也是阻塞的
                    inputStream.read(bytes);
                    System.out.println(System.currentTimeMillis() + " - " + Thread.currentThread().getName() + ": " + new String(bytes, 0, bytes.length));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }

    }

}

package com.milla.navicat.spring.study.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Package: com.milla.navicat.spring.study.io
 * @Description: <非阻塞式网络编程[通过选择器的监听，来确保有数据的时候才进行读取]>
 * @Author: MILLA
 * @CreateDate: 2020/4/14 15:19
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/4/14 15:19
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class NIODemo {
    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newCachedThreadPool();
        ServerSocketChannel channel = ServerSocketChannel.open();
        //绑定端口
        channel.bind(new InetSocketAddress(8080));
        System.out.println("NIO 启动 8080端口");
        //通过选择器去处理需要的连接
        Selector selector = Selector.open();
        while (true) {
            //获取一个连接
            SocketChannel accept = channel.accept();
            //设置是非阻塞的
            accept.configureBlocking(false);
            //注册一个事件通知机制，READ
            accept.register(selector, SelectionKey.OP_READ);


            selector.select();
            //获取所有的选择器的key
            Set<SelectionKey> keys = selector.keys();
            //遍历
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey event = iterator.next();
                SocketChannel connection = (SocketChannel) event.channel();
                // 连接是可读的
                if (event.isReadable()) {
                    pool.submit(() -> {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        try {
                            connection.read(buffer);
                            buffer.flip();
                            System.out.println(System.currentTimeMillis() + " - " + Thread.currentThread().getName() + ": " + new String(buffer.array()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }


            }
        }

    }
}

package com.milla.navicat.spring.study.distribute;


import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.IOException;

/**
 * @Package: com.milla.navicat.spring.study.distribute
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2020/3/19 14:13
 * @UpdateUser: MILLA
 * @UpdateDate: 2020/3/19 14:13
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Slf4j
public class ZkWatcherDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        ZkClient client = new ZkClient("hadoop-master:2181,hadoop-slave01:2181,hadoop-slave02:2181");
        client.setZkSerializer(new SerializableSerializer());
        client.subscribeDataChanges("/demo", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                log.error("收到节点数据变化信息：{}, {}", dataPath, data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                log.error("收到节点被删除的信息：{}", dataPath);
            }
        });
        Thread.sleep(1000 * 60 * 2);
    }
}

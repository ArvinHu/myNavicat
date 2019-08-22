package com.milla.navicat.config.datasource.dynamic;

/**
 * @Package: com.milla.navicat.config.datasource.dynamic
 * @Description: <数据源切换类>
 * @Author: MILLA
 * @CreateDate: 2019/8/21 10:41
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/21 10:41
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class DBContextHolder {
    // 对当前线程的操作-线程安全的
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 调用此方法，切换数据源
    public static void changeDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }

    // 获取数据源
    static String getDataSource() {
        return contextHolder.get();
    }

    // 删除数据源
    public static void clearDataSource() {
        contextHolder.remove();
    }
}

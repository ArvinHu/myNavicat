package com.milla.navicat.config.datasource;

import com.milla.navicat.config.datasource.dynamic.DataSourceVO;
import com.milla.navicat.config.datasource.dynamic.DatabaseCategory;
import com.milla.navicat.exception.DataSourceException;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.*;

/**
 * @Package: com.milla.navicat.config.datasource.dynamic
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/22 16:04
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/22 16:04
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class JDBCUtil {
    public static List<String> listDatabase(DataSourceVO dataSource) {
        checkedDatabaseParams(dataSource);
        List<String> list = new ArrayList<>();
        try {
            Connection connection = getConnection(dataSource);
            PreparedStatement databases = connection.prepareStatement("show databases;");
            ResultSet rs = databases.executeQuery();
            while (rs.next()) {
                String database = rs.getString("Database");
                list.add(database);
            }
            close(rs, null, connection);
            return list;
        } catch (SQLException e) {
            throw new DataSourceException("执行sql异常");
        }
    }

    public static Map<String, Set<String>> listCharacterEncodingAndCollation(DataSourceVO dataSource) {
        checkedDatabaseParams(dataSource);
        try {
            Connection connection = getConnection(dataSource);
            PreparedStatement databases = connection.prepareStatement("show collation;");
            ResultSet rs = databases.executeQuery();
            Map<String, Set<String>> charSets = new HashMap<>();
            while (rs.next()) {
                //字符集
                String charset = rs.getString("Charset");
                //校对规则
                String collation = rs.getString("Collation");
                if (!charSets.containsKey(charset)) {
                    charSets.put(charset, new HashSet<>());
                }
                Set<String> collations = charSets.get(charset);
                collations.add(collation);
            }
            close(rs, null, connection);
            return charSets;
        } catch (SQLException e) {
            throw new DataSourceException("执行sql异常");
        }
    }

    private static void checkedDatabaseParams(DataSourceVO dataSource) {
        if (dataSource.getDatabaseType() == null || StringUtils.isAnyBlank(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword())) {
            throw new DataSourceException("数据库连接参数缺失");
        }
    }

    public static boolean useDatabase(DataSourceVO dataSourceVO) {
        List<String> list = new ArrayList<>();
        try {
            Connection connection = getConnection(dataSourceVO);
            PreparedStatement databases = connection.prepareStatement("use " + dataSourceVO.getDatabase());
            boolean execute = databases.execute();
            close(null, null, connection);
            return execute;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //2.静态代码块（只执行一次）
    static {
        try {
            Class.forName(DatabaseCategory.MYSQL.getDriverClass());
//            Class.forName(DatabaseCategory.ORACLE.getDriverClass());
        } catch (ClassNotFoundException e) {
            throw new DataSourceException("加载不到驱动类");
        }
    }

    // 3.连接方法getConnection()
    public static Connection getConnection(DataSourceVO datasource) {
        try {
            return DriverManager.getConnection(datasource.getUrl(), datasource.getUsername(), datasource.getPassword());
        } catch (SQLException e) {
            throw new DataSourceException("获取数据库连接失败");
        }
    }

    //4.关闭连接close（ResultSet rs,Statemment st，Connection connection）
    private static void close(ResultSet resultset, Statement statement, Connection connection) {
        if (resultset != null) {
            try {
                resultset.close();
            } catch (SQLException e) {
                throw new DataSourceException("关闭ResultSet失败");
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DataSourceException("关闭Statement失败");
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DataSourceException("关闭connection失败");
            }
        }
    }
}

package com.milla.navicat.config.datasource;

import com.milla.navicat.config.datasource.dynamic.DataSourceVO;
import com.milla.navicat.config.datasource.dynamic.DatabaseCategory;
import com.milla.navicat.exception.DataSourceException;
import com.milla.navicat.pojo.vo.DatabaseVO;
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
public class MySqlJdbcUtil {
    public static List<String> listDatabase(DataSourceVO dataSource) {
        checkedDatabaseParams(dataSource);
        ResultSet rs = null;
        Connection connection = null;
        List<String> list = new ArrayList<>();
        try {
            connection = getConnection(dataSource);
            PreparedStatement databases = connection.prepareStatement("show databases;");
            rs = databases.executeQuery();
            while (rs.next()) {
                String database = rs.getString("Database");
                list.add(database);
            }
            return list;
        } catch (SQLException e) {
            throw new DataSourceException("执行sql异常");
        } finally {
            close(rs, null, connection);
        }
    }

    public static Map<String, Set<String>> listCharacterEncodingAndCollation(DataSourceVO dataSource) {
        checkedDatabaseParams(dataSource);
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection(dataSource);
            PreparedStatement databases = connection.prepareStatement("show collation;");
            rs = databases.executeQuery();
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
            return charSets;
        } catch (SQLException e) {
            throw new DataSourceException("执行sql异常");
        } finally {
            close(rs, null, connection);
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

    public static DatabaseVO getDatabaseByDatabaseName(DataSourceVO dataSource, String name) {
        checkedDatabaseParams(dataSource);
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection(dataSource);
            PreparedStatement databases = connection.prepareStatement("show create database `" + name + "`;");
            rs = databases.executeQuery();
            while (rs.next()) {
                DatabaseVO vo = new DatabaseVO();
                String description = rs.getString("Create Database");
                String collateKey = "COLLATE";
                String characterSetKey = "CHARACTER SET";
                int collate = description.lastIndexOf(collateKey);
                int characterSet = description.lastIndexOf(characterSetKey);
                if (collate != -1) {
                    vo.setOrderingRule(description.substring(collate + collateKey.length(), description.length() - 2).trim());
                    vo.setCharacterEncoding(description.substring(characterSet + characterSetKey.length(), collate).trim());
                    return vo;
                }
                vo.setCharacterEncoding(description.substring(characterSet + characterSetKey.length(), description.length() - 2).trim());
                return vo;
            }
            return null;
        } catch (SQLException e) {
            throw new DataSourceException("执行sql异常");
        } finally {
            close(rs, null, connection);
        }
    }
}

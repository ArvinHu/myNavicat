package com.milla.navicat.config.datasource.dynamic;

/**
 * @Package: com.milla.navicat.config.datasource.dynamic
 * @Description: <数据库种类>
 * @Author: MILLA
 * @CreateDate: 2019/8/22 13:32
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/22 13:32
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public enum DatabaseCategory {
    MYSQL(DBDriver.MYSQL, DBDriver.MYSQL_PORT, DBDriver.MYSQL_DATABASE, DBDriver.MYSQL_CLASS, DBDriver.MYSQL_URL),
    ORACLE(DBDriver.ORACLE, DBDriver.ORACLE_PORT, DBDriver.ORACLE_DATABASE, DBDriver.ORACLE_CLASS, DBDriver.ORACLE_URL),
    POSTGRES(DBDriver.POSTGRES, DBDriver.POSTGRES_PORT, DBDriver.POSTGRES_DATABASE, DBDriver.POSTGRES_CLASS, DBDriver.POSTGRES_URL),
    SQLSERVER(DBDriver.SQLSERVER, DBDriver.SQLSERVER_PORT, DBDriver.SQLSERVER_DATABASE, DBDriver.SQLSERVER_CLASS, DBDriver.SQLSERVER_URL);

    /**
     * @param category    数据库种类
     * @param port        端口
     * @param driverClass 驱动类
     * @param preJdbcUrl  url前置连接
     */
    DatabaseCategory(String category, int port, String preDatabase, String driverClass, String preJdbcUrl) {
        this.port = port;
        this.category = category;
        this.preJdbcUrl = preJdbcUrl;
        this.preDatabase = preDatabase;
        this.driverClass = driverClass;
    }

    private int port;
    private String category;
    private String driverClass;
    private String preJdbcUrl;
    private String preDatabase;

    public String getPreDatabase() {
        return preDatabase;
    }

    public String getCategory() {
        return category;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getPreJdbcUrl() {
        return preJdbcUrl;
    }

    public int getPort() {
        return port;
    }

    private class DBDriver {
        private static final String JDBC = "jdbc:";
        private static final String POSTGRES = "postgresql";
        private static final String POSTGRES_DATABASE = "/";
        private static final int POSTGRES_PORT = 5432;
        private static final String POSTGRES_URL = JDBC + "postgresql://";
        private static final String POSTGRES_CLASS = "org.postgresql.Drive";
        private static final String MYSQL = "MYSQL";
        private static final String MYSQL_DATABASE = "/";
        private static final int MYSQL_PORT = 3306;
        private static final String MYSQL_URL = JDBC + "mysql://";
        private static final String MYSQL_CLASS = "com.mysql.cj.jdbc.Driver";
        private static final String ORACLE = "ORACLE";
        private static final int ORACLE_PORT = 1521;
        private static final String ORACLE_DATABASE = ":";
        private static final String ORACLE_URL = JDBC + "oracle:thin:@";
        private static final String ORACLE_CLASS = "oracle.jdbc.driver.OracleDriver";
        private static final String SQLSERVER = "SQLSERVER";
        private static final int SQLSERVER_PORT = 1433;
        private static final String SQLSERVER_DATABASE = ";DatabaseName=";
        private static final String SQLSERVER_URL = JDBC + "microsoft:sqlserver://";
        private static final String SQLSERVER_CLASS = "com.microsoft.jdbc.sqlserver.SQLServerDriver";

        private static final String HSQL_PART = "HSQL";
        private static final String H2_PART = "H2";
        private static final String SYBASE_SQLANY_PART = "sql anywhere";
        private static final String SQLITE_PART = "SQLITE";
    }

    public static void main(String[] args) {
        System.out.println(DatabaseCategory.ORACLE.category);
        System.out.println(DatabaseCategory.ORACLE.driverClass);
        System.out.println(DatabaseCategory.ORACLE.preJdbcUrl);
        System.out.println(DatabaseCategory.ORACLE.getPort());
    }
}

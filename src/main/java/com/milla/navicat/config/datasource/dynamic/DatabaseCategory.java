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
    MYSQL(DBDriver.MYSQL, DBDriver.MYSQL_CLASS),
    ORACLE(DBDriver.ORACLE, DBDriver.ORACLE_CLASS),
    POSTGRES(DBDriver.POSTGRES, DBDriver.POSTGRES_CLASS),
    SQLSERVER(DBDriver.SQLSERVER, DBDriver.SQLSERVER_CLASS);


    DatabaseCategory(String category, String driverClass) {
        this.category = category;
        this.driverClass = driverClass;
    }

    private String category;
    private String driverClass;

    public String getCategory() {
        return category;
    }

    public String getDriverClass() {
        return driverClass;
    }

    private class DBDriver {
        private static final String POSTGRES = "postgresql";
        private static final String POSTGRES_CLASS = "org.postgresql.Drive";
        private static final String MYSQL = "mysql";
        private static final String MYSQL_CLASS = "com.mysql.cj.jdbc.Driver";
        private static final String ORACLE = "oracle";
        private static final String ORACLE_CLASS = "oracle.jdbc.driver.OracleDriver";
        private static final String SQLSERVER = "sqlserver";
        private static final String SQLSERVER_CLASS = "com.microsoft.jdbc.sqlserver.SQLServerDriver";

        private static final String HSQL_PART = "hsql";
        private static final String H2_PART = "h2";
        private static final String SYBASE_SQLANY_PART = "sql anywhere";
        private static final String SQLITE_PART = "sqlite";
    }

    public static void main(String[] args) {
        System.out.println(DatabaseCategory.MYSQL.category);
        System.out.println(DatabaseCategory.MYSQL.driverClass);
    }
}

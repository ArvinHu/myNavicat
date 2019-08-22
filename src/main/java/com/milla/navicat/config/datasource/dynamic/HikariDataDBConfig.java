package com.milla.navicat.config.datasource.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.milla.navicat.config.datasource.dynamic
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/21 10:38
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/21 10:38
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Slf4j
@Configuration
public class HikariDataDBConfig {


    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.username}")
    private String username;


    @Primary // 在同样的DataSource中，首先使用被标注的DataSource
    @Bean(name = "defaultDataSource") // 声明其为Bean实例
    public DataSource dataSource() {
        return HikariDataSourceUtil.initDatasource(driverClassName, jdbcUrl, username, password);
    }

    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDebug(true);
        dynamicDataSource.setDefaultTargetDataSource(dataSource());
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("defaultDataSource", dataSource());
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    @Bean(name = "dynamicJdbcTemplate")
    public NamedParameterJdbcTemplate dynamicJdbcTemplate(@Qualifier("dynamicDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Primary
    @Bean(name = "defaultJdbcTemplate")
    public NamedParameterJdbcTemplate defaultJdbcTemplate(@Qualifier("defaultDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

//    @Bean(name = "entityManagerFactory")
//    @Qualifier("entityManagerFactory")
//    @Primary
//    public LocalContainerEntityManagerFactoryBean entityManageFactory(EntityManagerFactoryBuilder builder) throws SQLException {
//        LocalContainerEntityManagerFactoryBean entityManagerFactory = builder.dataSource(dataSource()).packages("com.bitservice.adi.entity").build();
//        Properties jpaProperties = new Properties();
//        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
//        jpaProperties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
//        jpaProperties.put("hibernate.connection.charSet", "utf-8");
//        jpaProperties.put("hibernate.show_sql", "false");
//        entityManagerFactory.setJpaProperties(jpaProperties);
//        return entityManagerFactory;
//    }
//
//    @Bean(name = "entityManager")
//    @Qualifier("entityManager")
//    @Primary
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) throws SQLException {
//        return entityManageFactory(builder).getObject().createEntityManager();
//    }
//
//
//    @Bean(name = "dynamicEntityManageFactory")
//    @Qualifier("dynamicEntityManageFactory")
//    public LocalContainerEntityManagerFactoryBean dynamicEntityManageFactory(EntityManagerFactoryBuilder builder) throws SQLException {
//        LocalContainerEntityManagerFactoryBean entityManagerFactory = builder.dataSource(dynamicDataSource()).packages("com.bitservice.dynamic.entity").build();
//        Properties jpaProperties = new Properties();
//        //jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        jpaProperties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
//        jpaProperties.put("hibernate.connection.charSet", "utf-8");
//        jpaProperties.put("hibernate.show_sql", "false");
//        entityManagerFactory.setJpaProperties(jpaProperties);
//        return entityManagerFactory;
//    }
//
//    @Bean(name = "dynamicEntityManage")
//    @Qualifier("dynamicEntityManage")
//    public EntityManager dynamicEntityManage(EntityManagerFactoryBuilder builder) throws SQLException {
//        return entityManageFactory(builder).getObject().createEntityManager();
//    }
// adi数据库连接信息
//@Value("${spring.datasource.url}")
//private String dbUrl;
//    @Value("${spring.datasource.username}")
//    private String username;
//    @Value("${spring.datasource.password}")
//    private String password;
//    @Value("${spring.datasource.driverClassName}")
//    private String driverClassName;
//
//    // 连接池连接信息
//    @Value("${spring.datasource.initialSize}")
//    private int initialSize;
//    @Value("${spring.datasource.minIdle}")
//    private int minIdle;
//    @Value("${spring.datasource.maxActive}")
//    private int maxActive;
//    @Value("${spring.datasource.maxWait}")
//    private int maxWait;
//
//    @Bean // 声明其为Bean实例
//    @Primary // 在同样的DataSource中，首先使用被标注的DataSource
//    @Qualifier("adiDataSource")
//    public DataSource dataSource() throws SQLException {
//        DruidDataSource datasource = new DruidDataSource();
//        // 基础连接信息
//        datasource.setUrl(this.dbUrl);
//        datasource.setUsername(username);
//        datasource.setPassword(password);
//        datasource.setDriverClassName(driverClassName);
//        // 连接池连接信息
//        datasource.setInitialSize(initialSize);
//        datasource.setMinIdle(minIdle);
//        datasource.setMaxActive(maxActive);
//        datasource.setMaxWait(maxWait);
//
//        datasource.setPoolPreparedStatements(true); //是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
//        datasource.setMaxPoolPreparedStatementPerConnectionSize(50);
//        datasource.setConnectionProperties("oracle.net.CONNECT_TIMEOUT=6000;oracle.jdbc.ReadTimeout=60000");//对于耗时长的查询sql，会受限于ReadTimeout的控制，单位毫秒
//        datasource.setTestOnBorrow(true); //申请连接时执行validationQuery检测连接是否有效，这里建议配置为TRUE，防止取到的连接不可用
//        datasource.setTestWhileIdle(true);//建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
//        String validationQuery = "select 1 from dual";
//        datasource.setValidationQuery(validationQuery); //用来检测连接是否有效的sql，要求是一个查询语句。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。
//        datasource.setFilters("stat,wall");//属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat日志用的filter:log4j防御sql注入的filter:wall
//        datasource.setTimeBetweenEvictionRunsMillis(60000); //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
//        datasource.setMinEvictableIdleTimeMillis(180000); //配置一个连接在池中最小生存的时间，单位是毫秒，这里配置为3分钟180000
//        datasource.setKeepAlive(true); //打开druid.keepAlive之后，当连接池空闲时，池中的minIdle数量以内的连接，空闲时间超过minEvictableIdleTimeMillis，则会执行keepAlive操作，即执行druid.validationQuery指定的查询SQL，一般为select * from dual，只要minEvictableIdleTimeMillis设置的小于防火墙切断连接时间，就可以保证当连接空闲时自动做保活检测，不会被防火墙切断
//
//        datasource.setRemoveAbandoned(true); //是否移除泄露的连接/超过时间限制是否回收。
//        datasource.setRemoveAbandonedTimeout(3600); //泄露连接的定义时间(要超过最大事务的处理时间)；单位为秒。这里配置为1小时
//        datasource.setLogAbandoned(true); ////移除泄露连接发生是是否记录日志
//        return datasource;
//    }
}

package com.milla.navicat.config.datasource.dynamic;

import com.github.pagehelper.PageInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

    @Bean
    public PageInterceptor pageHelper() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
//        properties.setProperty("dialect", SQLDialectCode.MYSQL_DIALECT.name());    //配置mysql数据库的方言
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(PageInterceptor pageHelper, @Qualifier("defaultDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
        sqlSessionFactory.setPlugins(new Interceptor[]{pageHelper});
        sqlSessionFactory.setTypeAliasesPackage("com.milla.navicat.mapper");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCallSettersOnNulls(true);
        //全局字段配置
//        Properties properties = new Properties();
        //数据库实例名称
//        properties.setProperty("tableSchema", tableSchema);
//        configuration.setVariables(properties);
        sqlSessionFactory.setConfiguration(configuration);
        return sqlSessionFactory.getObject();
    }
}

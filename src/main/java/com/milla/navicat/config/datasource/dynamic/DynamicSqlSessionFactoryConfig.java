package com.milla.navicat.config.datasource.dynamic;

import com.github.pagehelper.PageInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan(basePackages = "com.milla.navicat.mapper", sqlSessionFactoryRef = "dynamicSqlSessionFactoryBean")
public class DynamicSqlSessionFactoryConfig {

    @Bean(name = "dynamicSqlSessionFactoryBean")
    public SqlSessionFactoryBean dynamicSqlSessionFactoryBean(PageInterceptor pageHelper, @Qualifier("dynamicDataSource") DynamicDataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
        sqlSessionFactory.setPlugins(new Interceptor[]{pageHelper});
        sqlSessionFactory.setTypeAliasesPackage("com.milla.navicat.mapper.dynamic");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCallSettersOnNulls(true);
        //全局字段配置
//        Properties properties = new Properties();
        //数据库实例名称
//        properties.setProperty("tableSchema", tableSchema);
//        configuration.setVariables(properties);
        sqlSessionFactory.setConfiguration(configuration);
        return sqlSessionFactory;
    }

//    @Bean("dynamicSqlSessionFactory")
//    public SqlSessionFactory dynamicSqlSessionFactory(@Qualifier("dynamicSqlSessionFactoryBean") SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
//        return sqlSessionFactoryBean.getObject();
//    }
}

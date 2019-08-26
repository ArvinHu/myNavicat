package com.milla.navicat.service.impl;

import com.milla.navicat.config.datasource.dynamic.DataSourceVO;
import com.milla.navicat.exception.DataSourceException;
import com.milla.navicat.pojo.vo.DatabaseVO;
import com.milla.navicat.service.IDatabaseService;
import com.milla.navicat.service.IShowService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.milla.navicat.service.impl
 * @Description: <数据库操作类>
 * @Author: MILLA
 * @CreateDate: 2019/8/23 9:37
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/23 9:37
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Service
public class DatabaseServiceImpl implements IDatabaseService {
    @Autowired
    @Qualifier("defaultJdbcTemplate")
    private NamedParameterJdbcTemplate template;
    @Autowired
    private IShowService service;

    @Override
    public void changeDatabase(DataSourceVO datasource) {

    }

    @Override
    @Transactional
    public int addDatabase(DatabaseVO database) {
        //默认字段集
        if (StringUtils.isBlank(database.getCharacterEncoding())) {
            database.setCharacterEncoding("utf8");
        }
        //默认排序规则
        if (StringUtils.isBlank(database.getOrderingRule())) {
            database.setOrderingRule("utf8_spanish_ci");
        }
        List<String> databases = service.listDatabase(database.getConnId());
        if (database != null && databases.contains(database.getDatabaseName())) {
            throw new DataSourceException("数据已经存在");
        }
        String sql = "CREATE DATABASE `" + database.getDatabaseName() + "` CHARACTER SET '" + database.getCharacterEncoding() + "' COLLATE '" + database.getOrderingRule() + "'";
        template.update(sql, new HashMap<>());
        return 0;
    }
}
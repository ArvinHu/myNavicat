package com.milla.navicat.service.impl;

import com.milla.navicat.config.datasource.dynamic.DataSourceVO;
import com.milla.navicat.service.IDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

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

    @Override
    public void changeDatabase(DataSourceVO datasource) {

    }
}

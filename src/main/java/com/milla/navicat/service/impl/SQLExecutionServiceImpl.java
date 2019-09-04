package com.milla.navicat.service.impl;

import com.milla.navicat.mapper.dynamic.SQLExecuteMapper;
import com.milla.navicat.service.ISQLExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.milla.navicat.service.impl
 * @Description: <直接使用命令行业务类>
 * @Author: MILLA
 * @CreateDate: 2019/9/4 9:43
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/9/4 9:43
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Service
public class SQLExecutionServiceImpl implements ISQLExecutionService {

    @Autowired
    private SQLExecuteMapper mapper;

    @Override
    public void updateByExecuteSQL(String sql) {
        Assert.hasText(sql, "SQL不能为空");
        mapper.updateBySQL(sql);
    }

    @Override
    public List<Map<Object, Object>> listObjectByExecuteSQL(String sql) {
        Assert.hasText(sql, "SQL不能为空");
        return mapper.selectObjectListBySQL(sql);
    }
}

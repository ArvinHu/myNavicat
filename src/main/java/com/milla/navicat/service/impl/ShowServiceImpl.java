package com.milla.navicat.service.impl;

import com.google.common.collect.Sets;
import com.milla.navicat.comm.ResponseData;
import com.milla.navicat.config.datasource.JDBCUtil;
import com.milla.navicat.config.datasource.dynamic.DataSourceVO;
import com.milla.navicat.pojo.dto.DatabaseConnectionDTO;
import com.milla.navicat.service.IDatabaseConnectionService;
import com.milla.navicat.service.IShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Package: com.milla.navicat.service.impl
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/16 14:09
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/16 14:09
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Service
public class ShowServiceImpl implements IShowService {
    @Autowired
    private IDatabaseConnectionService service;

    @Override
    public List<String> listDatabase(Integer connId) {
        DatabaseConnectionDTO connection = service.getConnectionByConnId(connId);
        DataSourceVO dataSourceVO = service.getDataSourceVO(connection);
        return JDBCUtil.listDatabase(dataSourceVO);
    }

    @Override
    public ResponseData listTable(DataSourceVO dataSource) {
        return null;
    }

    @Override
    public ResponseData listView() {
        return null;
    }

    @Override
    public ResponseData listFunction() {
        return null;
    }

    @Override
    public ResponseData listEvent() {
        return null;
    }

    @Override
    public List<String> listCharacterEncoding(Integer connId) {
        DatabaseConnectionDTO connection = service.getConnectionByConnId(connId);
        DataSourceVO dataSourceVO = service.getDataSourceVO(connection);
        Map<String, Set<String>> stringSetMap = JDBCUtil.listCharacterEncodingAndCollation(dataSourceVO);
        Set<String> keySet = stringSetMap.keySet();
        if (keySet == null || keySet.isEmpty()) {
            return null;
        }
        List<String> arrayList = new ArrayList(keySet);
        Collections.sort(arrayList);
        return arrayList;
    }

    @Override
    public List<String> listOrderingRuleByCharacterEncoding(Integer connId, String character) {
        DatabaseConnectionDTO connection = service.getConnectionByConnId(connId);
        DataSourceVO dataSourceVO = service.getDataSourceVO(connection);
        Map<String, Set<String>> stringSetMap = JDBCUtil.listCharacterEncodingAndCollation(dataSourceVO);
        Set<String> rules = stringSetMap.get(character);
        if (rules == null || rules.isEmpty()) {
            return null;
        }
        List<String> arrayList = new ArrayList(rules);
        Collections.sort(arrayList);
        return arrayList;
    }
}

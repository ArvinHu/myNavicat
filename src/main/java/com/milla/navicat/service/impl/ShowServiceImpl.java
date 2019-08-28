package com.milla.navicat.service.impl;

import com.milla.navicat.comm.ResponseData;
import com.milla.navicat.config.datasource.MySqlJdbcUtil;
import com.milla.navicat.config.datasource.dynamic.DataSourceVO;
import com.milla.navicat.exception.CustomMessageException;
import com.milla.navicat.pojo.dto.DatabaseConnectionDTO;
import com.milla.navicat.pojo.vo.DatabaseVO;
import com.milla.navicat.service.IDatabaseConnectionService;
import com.milla.navicat.service.IShowService;
import org.apache.commons.collections4.CollectionUtils;
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
        return MySqlJdbcUtil.listDatabase(dataSourceVO);
    }

    @Override
    public ResponseData listTable() {
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
        Map<String, Set<String>> stringSetMap = MySqlJdbcUtil.listCharacterEncodingAndCollation(dataSourceVO);
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
        Map<String, Set<String>> stringSetMap = MySqlJdbcUtil.listCharacterEncodingAndCollation(dataSourceVO);
        Set<String> rules = stringSetMap.get(character);
        if (rules == null || rules.isEmpty()) {
            return null;
        }
        List<String> arrayList = new ArrayList(rules);
        Collections.sort(arrayList);
        return arrayList;
    }

    @Override
    public DatabaseVO getDatabaseByDatabaseName(Integer connId, String databaseName) {
        String message = "数据库信息不存在";
        DatabaseConnectionDTO connection = service.getConnectionByConnId(connId);
        DataSourceVO dataSourceVO = service.getDataSourceVO(connection);
        List<String> databases = MySqlJdbcUtil.listDatabase(dataSourceVO);
        if (CollectionUtils.isEmpty(databases) || !databases.contains(databaseName)) {
            throw new CustomMessageException(message);
        }
        DatabaseVO vo = MySqlJdbcUtil.getDatabaseByDatabaseName(dataSourceVO, databaseName);
        if (vo == null) {
            throw new CustomMessageException(message);
        }
        vo.setConnId(connId);
        vo.setDatabaseName(databaseName);
        return vo;
    }
}

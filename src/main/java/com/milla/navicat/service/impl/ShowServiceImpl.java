package com.milla.navicat.service.impl;

import com.milla.navicat.comm.ResponseData;
import com.milla.navicat.config.datasource.JDBCUtil;
import com.milla.navicat.config.datasource.dynamic.DataSourceVO;
import com.milla.navicat.service.IShowService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public List<String> listDatabase(DataSourceVO dataSource) {
        return JDBCUtil.listDatabase(dataSource);
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
}

package com.milla.navicat.service.impl;

import com.milla.navicat.mapper.dynamic.TableIndexDTOMapper;
import com.milla.navicat.pojo.vo.TableIndexVO;
import com.milla.navicat.service.ITableIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.milla.navicat.service.impl
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/30 19:51
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/30 19:51
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Service
public class TableIndexServiceImpl implements ITableIndexService {
    @Autowired
    private TableIndexDTOMapper mapper;

    @Override
    public List<TableIndexVO> listIndexByTableName(String tableName) {
        return mapper.selectIndexListByTableName(tableName);
    }
}

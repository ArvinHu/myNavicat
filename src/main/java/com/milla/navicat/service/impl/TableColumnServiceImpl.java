package com.milla.navicat.service.impl;

import com.milla.navicat.pojo.vo.TableColumnVO;
import com.milla.navicat.service.ITableColumnService;
import com.milla.navicat.util.WebUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.milla.navicat.service.impl
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/28 16:20
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/28 16:20
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Service
public class TableColumnServiceImpl implements ITableColumnService {
    @Override
    public List<TableColumnVO> listFieldsByTableName(String tableName) {
        String tableSchema = WebUtil.currentTableSchema();
        return null;
    }
}

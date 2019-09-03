package com.milla.navicat.service;

import com.milla.navicat.pojo.dto.TableForeignKeyDTO;

import java.util.List;

/**
 * @Package: com.milla.navicat.service
 * @Description: <表格外键逻辑类>
 * @Author: MILLA
 * @CreateDate: 2019/9/2 16:27
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/9/2 16:27
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public interface ITableForeignKeyService {
    List<TableForeignKeyDTO> listForeignKeyByTableName(String tableName);

    String validateTableForeignKey(TableForeignKeyDTO dto);

    void updateTableForeignKey(String sql);
}

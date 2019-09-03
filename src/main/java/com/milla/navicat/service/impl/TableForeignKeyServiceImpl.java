package com.milla.navicat.service.impl;

import com.milla.navicat.mapper.dynamic.SQLExecuteMapper;
import com.milla.navicat.mapper.dynamic.TableForeignKeyDTOMapper;
import com.milla.navicat.pojo.dto.TableForeignKeyDTO;
import com.milla.navicat.service.ITableForeignKeyService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.milla.navicat.service.impl
 * @Description: <外键业务类>
 * @Author: MILLA
 * @CreateDate: 2019/9/2 17:35
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/9/2 17:35
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Service
public class TableForeignKeyServiceImpl implements ITableForeignKeyService {
    @Autowired
    private TableForeignKeyDTOMapper mapper;
    @Autowired
    private SQLExecuteMapper executeMapper;

    @Override
    public List<TableForeignKeyDTO> listForeignKeyByTableName(String tableName) {
        Assert.hasText(tableName, "表格名称不能为空");

        //TODO 数据库需要设置
        return mapper.selectForeignKeyListByTableName("tcc_order", tableName);
    }

    @Override
    public String validateTableForeignKey(TableForeignKeyDTO dto) {
        Assert.hasText(dto.getTableName(), "表格名称不能为空");
        Assert.hasText(dto.getColumnName(), "表格字段不能为空");
        Assert.hasText(dto.getReferencedTableName(), "参考表格名不能为空");
        Assert.hasText(dto.getReferencedColumnName(), "参考表格字段不能为空");
        Assert.hasText(dto.getReferencedTableSchema(), "参考模式不能为空");
        String sql = "FOREIGN KEY (`%s`) REFERENCES `%s`.`%s` (`%s`) ";
        sql = String.format(sql, dto.getColumnName(), dto.getReferencedTableSchema(), dto.getReferencedTableName(), dto.getReferencedColumnName());
        StringBuilder sb = new StringBuilder(sql);
        //删除的规则
        if (dto.getDeleteRule() != null) {
            sb.append(" ON DELETE").append(dto.getDeleteRule().getCode());
        }
        //更新的规则
        if (dto.getUpdateRule() != null) {
            sb.append(" ON DELETE").append(dto.getUpdateRule().getCode());
        }
        return sb.toString();
    }

    @Override
    public void updateTableForeignKey(String sql) {
        Assert.hasText(sql, "SQL不能为空");
        executeMapper.updateBySQL(sql);
    }
}

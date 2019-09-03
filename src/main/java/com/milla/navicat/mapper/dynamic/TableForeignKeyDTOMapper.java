package com.milla.navicat.mapper.dynamic;

import com.milla.navicat.pojo.dto.TableForeignKeyDTO;
import com.milla.navicat.pojo.vo.TableForeignKeyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.milla.navicat.mapper.dynamic
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/30 19:57
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/30 19:57
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public interface TableForeignKeyDTOMapper {
    List<TableForeignKeyDTO> selectForeignKeyListByTableName(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

    void alterTableIndex(TableForeignKeyVO column);
}

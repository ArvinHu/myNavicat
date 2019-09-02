package com.milla.navicat.mapper.dynamic;

import com.milla.navicat.pojo.dto.TableColumnDTO;
import com.milla.navicat.pojo.vo.TableColumnVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.milla.navicat.mapper.dynamic
 * @Description: <表格列数据库操作类>
 * @Author: MILLA
 * @CreateDate: 2019/8/29 16:48
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/29 16:48
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public interface TableColumnDTOMapper {
    List<TableColumnDTO> selectColumnListByTableName(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

    void dropTableColumn(TableColumnVO column);
}

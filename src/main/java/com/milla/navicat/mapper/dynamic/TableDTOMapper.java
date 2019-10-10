package com.milla.navicat.mapper.dynamic;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.milla.navicat.mapper.dynamic
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/27 19:53
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/27 19:53
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public interface TableDTOMapper {
    List<String> selectTableList();

    void createTable(@Param("sql") String sql);

    void dropTable(@Param("tableName") String tableName);

    void alterTableName(@Param("tableName") String tableName, @Param("newName") String newName);

    void deleteTableData(@Param("tableName") String tableName);

    void createTableOnlyStructureByCopy(@Param("tableName") String tableName, @Param("newTableName") String newTableName);

    void createTableByCopy(String tableName, String newTableName);

    void alterTableCommentByName(@Param("tableName") String tableName, @Param("tableComment") String tableComment);

    List<Map<String, Object>> selectTableDataList(@Param("tableName") String tableName, @Param("columnNameList") String columnNameList);
}

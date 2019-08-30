package com.milla.navicat.handler;

import com.milla.navicat.config.datasource.dynamic.DatabaseCategory;
import com.milla.navicat.pojo.enums.EnumTableIndex;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Package: com.milla.navicat.handler
 * @Description: <自定义数据库枚举操作类>
 * @Author: MILLA
 * @CreateDate: 2019/8/25 7:32
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/25 7:32
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class TableIndexCategoryHandler extends BaseTypeHandler<EnumTableIndex> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, EnumTableIndex databaseCategory, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, databaseCategory.name());
    }

    @Override
    public EnumTableIndex getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String category = resultSet.getString(columnName);
        return EnumTableIndex.valueOf(category);
    }

    @Override
    public EnumTableIndex getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String category = resultSet.getString(columnIndex);
        return EnumTableIndex.valueOf(category);
    }

    @Override
    public EnumTableIndex getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String category = callableStatement.getString(columnIndex);
        return EnumTableIndex.valueOf(category);
    }
}

package com.milla.navicat.handler;

import com.milla.navicat.pojo.enums.EnumTableForeignKeyRule;
import com.milla.navicat.pojo.enums.EnumTableIndexCategory;
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
public class TableForeignKeyRuleHandler extends BaseTypeHandler<EnumTableForeignKeyRule> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, EnumTableForeignKeyRule databaseCategory, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, databaseCategory.getCode());
    }

    @Override
    public EnumTableForeignKeyRule getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String category = resultSet.getString(columnName);
        return EnumTableForeignKeyRule.valueOf(category.replace(" ", "_"));
    }

    @Override
    public EnumTableForeignKeyRule getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String category = resultSet.getString(columnIndex);
        return EnumTableForeignKeyRule.valueOf(category.replace(" ", "_"));
    }

    @Override
    public EnumTableForeignKeyRule getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String category = callableStatement.getString(columnIndex);
        return EnumTableForeignKeyRule.valueOf(category.replace(" ", "_"));
    }
}

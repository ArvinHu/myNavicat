package com.milla.navicat.handler;

import com.milla.navicat.config.datasource.dynamic.DatabaseCategory;
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
public class DatabaseCategoryHandler extends BaseTypeHandler<DatabaseCategory> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, DatabaseCategory databaseCategory, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, databaseCategory.getCategory());
    }

    @Override
    public DatabaseCategory getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String category = resultSet.getString(columnName);
        return category(category);
    }

    @Override
    public DatabaseCategory getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String category = resultSet.getString(columnIndex);
        return category(category);
    }

    @Override
    public DatabaseCategory getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String category = callableStatement.getString(columnIndex);
        return category(category);
    }

    private DatabaseCategory category(String category) {
        DatabaseCategory[] categoryList = DatabaseCategory.values();
        for (DatabaseCategory databaseCategory : categoryList) {
            if (databaseCategory.getCategory().equals(category)) {
                return databaseCategory;
            }
        }
        return null;
    }
}

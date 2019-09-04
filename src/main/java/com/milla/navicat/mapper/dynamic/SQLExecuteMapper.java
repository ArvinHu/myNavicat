package com.milla.navicat.mapper.dynamic;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.milla.navicat.mapper.dynamic
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/9/3 14:55
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/9/3 14:55
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public interface SQLExecuteMapper {
    void updateBySQL(@Param("sql") String sql);

    List<Map<Object, Object>> selectObjectListBySQL(@Param("sql") String sql);
}

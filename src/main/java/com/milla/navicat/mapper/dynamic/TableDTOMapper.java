package com.milla.navicat.mapper.dynamic;

import java.util.List;

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

    void alterTable(String sql);
}

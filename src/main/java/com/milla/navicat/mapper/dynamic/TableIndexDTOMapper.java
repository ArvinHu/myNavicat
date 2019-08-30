package com.milla.navicat.mapper.dynamic;

import com.milla.navicat.pojo.vo.TableIndexVO;
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
public interface TableIndexDTOMapper {
    List<TableIndexVO> selectIndexListByTableName(@Param("tableName") String tableName);
}

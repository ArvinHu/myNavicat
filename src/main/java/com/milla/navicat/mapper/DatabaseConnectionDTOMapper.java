package com.milla.navicat.mapper;

import com.milla.navicat.pojo.dto.DatabaseConnectionDTO;

public interface DatabaseConnectionDTOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DatabaseConnectionDTO record);

    int insertSelective(DatabaseConnectionDTO record);

    DatabaseConnectionDTO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DatabaseConnectionDTO record);

    int updateByPrimaryKey(DatabaseConnectionDTO record);
}
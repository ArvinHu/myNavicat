package com.milla.navicat.mapper;

import com.milla.navicat.pojo.dto.DatasourceDTO;

public interface DatasourceDTOMapper {
    int deleteByPrimaryKey(String datasourceId);

    int insert(DatasourceDTO record);

    int insertSelective(DatasourceDTO record);

    DatasourceDTO selectByPrimaryKey(String datasourceId);

    int updateByPrimaryKeySelective(DatasourceDTO record);

    int updateByPrimaryKey(DatasourceDTO record);
}
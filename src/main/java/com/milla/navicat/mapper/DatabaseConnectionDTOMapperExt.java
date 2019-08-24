package com.milla.navicat.mapper;

import com.milla.navicat.pojo.dto.DatabaseConnectionDTO;

import java.util.List;

public interface DatabaseConnectionDTOMapperExt {
    List<DatabaseConnectionDTO> selectConnectionListByAccount(String account);
}
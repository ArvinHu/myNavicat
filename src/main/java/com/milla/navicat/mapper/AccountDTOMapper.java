package com.milla.navicat.mapper;

import com.milla.navicat.pojo.dto.AccountDTO;

public interface AccountDTOMapper {
    int deleteByPrimaryKey(String account);

    int insert(AccountDTO record);

    int insertSelective(AccountDTO record);

    AccountDTO selectByPrimaryKey(String account);

    int updateByPrimaryKeySelective(AccountDTO record);

    int updateByPrimaryKey(AccountDTO record);
}
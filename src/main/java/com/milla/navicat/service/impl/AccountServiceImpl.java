package com.milla.navicat.service.impl;

import com.milla.navicat.exception.CustomMessageException;
import com.milla.navicat.mapper.AccountDTOMapper;
import com.milla.navicat.pojo.dto.AccountDTO;
import com.milla.navicat.pojo.vo.AccountVO;
import com.milla.navicat.pojo.vo.TokenVO;
import com.milla.navicat.service.IAccountService;
import com.milla.navicat.util.WebUtil;
import com.milla.navicat.util.token.JwtTokenProvider;
import com.milla.navicat.util.token.MD5Util;
import io.jsonwebtoken.impl.DefaultClaims;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

import static com.milla.navicat.constant.Constant.C_CURRENT_ACCOUNT;
import static com.milla.navicat.constant.HeaderParamConstant.C_TOKEN;

/**
 * @Package: com.milla.navicat.service.impl
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/16 10:40
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/16 10:40
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountDTOMapper mapper;

    @Override
    public String login(AccountVO account) {
        //1.校验帐号不能为空
        Assert.hasText(account.getAccount(), "账户不能为空");
        //2.校验密码不能为空
        Assert.hasText(account.getPassword(), "密码不能为空");
        //3.按照输入的帐号去查询帐号信息
        AccountDTO accountDTO = mapper.selectByPrimaryKey(account.getAccount());
        //4.校验帐号信息是否存在
        if (accountDTO == null) {
            throw new CustomMessageException("用户不存在");
        }
        //5.校验密码是否正确
        if (!StringUtils.equals(account.getPassword(), accountDTO.getPassword())) {
            throw new CustomMessageException("用户密码错误");
        }
        //6：生成token(令牌)q
        String key = MD5Util.md5Sorted(account.getAccount() + account.getPassword() + System.nanoTime());
        DefaultClaims info = new DefaultClaims();
        info.put(C_CURRENT_ACCOUNT, account);
        JwtTokenProvider provider = new JwtTokenProvider(key);
        String token = provider.createToken(info);
        TokenVO tokenInfo = new TokenVO();
        tokenInfo.setAccount(account.getAccount());
        tokenInfo.setToken(token);
        tokenInfo.setTokenKey(key);
        tokenInfo.setExpiration(DateUtils.addDays(new Date(), 30).getTime());
        WebUtil.getSession().setAttribute(C_TOKEN, tokenInfo);
        System.out.println(WebUtil.currentAccount() + "////////" + WebUtil.getSession().getId());
        return token;
    }

}

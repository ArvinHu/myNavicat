package com.milla.navicat.service.impl;

import com.milla.navicat.config.datasource.dynamic.DynamicDataSource;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.milla.navicat.constant.Constant.C_TOKEN;

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
    @Qualifier("defaultJdbcTemplate")
    NamedParameterJdbcTemplate template;
    @Autowired
    @Qualifier("dynamicJdbcTemplate")
    NamedParameterJdbcTemplate dynamicJdbcTemplate;
    @Autowired
    @Qualifier("dynamicDataSource")
    private DynamicDataSource dynamicDataSource;
    @Autowired
    private AccountDTOMapper mapper;

    @Override
    public String login(AccountVO account) {
        Assert.notNull(account.getAccount(), "账户不能为空");
        Assert.notNull(account.getPassword(), "密码不能为空");
        AccountDTO accountDTO = mapper.selectByPrimaryKey(account.getAccount());
        if (accountDTO == null) {
            throw new CustomMessageException("用户不存在");
        }
        if (!StringUtils.equals(account.getPassword(), accountDTO.getPassword())) {
            throw new CustomMessageException("用户密码错误");
        }
//        HashMap<String, Object> objectObjectHashMap = Maps.newHashMap();
//        Map<String, Object> map1 = dynamicJdbcTemplate.queryForMap("select * from user where id=1", objectObjectHashMap);
//        System.out.println("查询的结果为 dynamicJdbcTemplate-第一次查询结果 ：" + map1);
//        DataSourceVO dataSource = new DataSourceVO();
//        dataSource.setUsername("dev");
//        dataSource.setPassword("123456");
////        dataSource.setUrl("jdbc:mysql://118.178.195.63:3306/crrc_test?characterEncoding=utf-8&allowMultiQueries=true");
//        dataSource.setDatabaseType(DatabaseCategory.MYSQL);
//        dataSource.setHost("118.178.195.63");
//        dataSource.setDatabase("crrc_test");
//        dataSource.setDatasourceId(dataSource.getHost() + "-" + dataSource.getDatabase());
//
//        try {
//            dynamicDataSource.createDataSourceWithCheck(dataSource);
//            DBContextHolder.changeDataSource(dataSource.getDatasourceId());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Map<String, Object> map2 = dynamicJdbcTemplate.queryForMap("select * from user where id=1", objectObjectHashMap);
//        System.out.println("------------------------------------------------------------");
//        System.out.println("查询的结果为 dynamicJdbcTemplate-第二次查询结果 ：" + map2);
//        System.out.println("------------------------------------------------------------");
        //1：校验用户名和密码
        //2：生成token(令牌)
        String key = MD5Util.md5Sorted(account.getAccount() + account.getPassword() + System.nanoTime());
        DefaultClaims info = new DefaultClaims();
        info.put("account", account);
        JwtTokenProvider provider = new JwtTokenProvider(key);
        String token = provider.createToken(info);
        TokenVO tokenInfo = new TokenVO();
        tokenInfo.setAccount(account.getAccount());
        tokenInfo.setToken(token);
        tokenInfo.setTokenKey(key);
        tokenInfo.setExpiration(10000L);
        WebUtil.getSession().setAttribute(C_TOKEN, tokenInfo);
        return token;
    }

}

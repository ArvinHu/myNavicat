package com.milla.navicat.pojo.vo;

/**
 * @Package: com.milla.navicat.pojo.vo
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/20 15:00
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/20 15:00
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class TokenVO {
    //用户票据
    private String token;
    //票据key
    private String tokenKey;
    //用户帐号
    private String account;
    //用户密码
    private String password;
    //过期时间(时间戳)
    private long expiration;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return "TokenVO{" +
                "token='" + token + '\'' +
                ", tokenKey='" + tokenKey + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", expiration=" + expiration +
                '}';
    }
}

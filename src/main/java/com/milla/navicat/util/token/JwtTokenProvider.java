package com.milla.navicat.util.token;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

/**
 * @Package: com.milla.navicat.util.token
 * @Description: <>
 * @Author: MILLA
 * @CreateDate: 2019/8/16 11:23
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/16 11:23
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public class JwtTokenProvider {

    private Key signingKey;

    public JwtTokenProvider(String key) {
        this.signingKey = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * 创建token
     *
     * @param claims token中包含的信息
     * @return 生成的token信息
     */
    public String createToken(Claims claims) {
        return createToken(claims, null);
    }

    /**
     * @param claims    参数
     * @param expMillis 过期时间
     * @return
     */
    private String createToken(Claims claims, Long expMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setIssuedAt(now).setClaims(claims).compressWith(CompressionCodecs.DEFLATE);
        if (expMillis != null && expMillis >= 0L) {
            expMillis = nowMillis + expMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解析token
     *
     * @param token token字符串
     * @return token中包含的信息
     */
    public Claims parseToken(String token) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


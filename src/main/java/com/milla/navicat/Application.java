package com.milla.navicat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//tesToken();
    }

//    private static void tesToken() {
//        JwtConfig config = new JwtConfig();
//        config.setIssuer("我是签发者");
//        config.setSecret("test");
//        config.setSubject("我的用户群体");
//        config.setTtlMillis(1000000000L);
//        JwtTokenProvider provider = new JwtTokenProvider(config);
//        Main.UserContext context = new Main.UserContext();
//        context.setUserId(123L);
//        context.setName("konghang");
//        String jwt = provider.createJwtToken(context);
//        System.out.println(jwt);
//
//
//        Jws<Claims> claimsJws = provider.parseJwtToken(jwt);
//        String signature = claimsJws.getSignature();
//        JwsHeader header = claimsJws.getHeader();
//        Claims body = claimsJws.getBody();
//        System.out.println(header);
//        System.out.println(signature);
//        Payload payload1 = provider.parseJwtToken(jwt, Main.UserContext.class);
//        System.out.println(((Main.UserContext) payload1).getName());
//        System.out.println(((Main.UserContext) payload1).getUserId());
//    }

}


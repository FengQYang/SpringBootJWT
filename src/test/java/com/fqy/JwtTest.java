package com.fqy;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.HashMap;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class JwtTest {

    @Test
    public void contextLoads(){
        HashMap<String, Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,200);
        String token = JWT.create()
//             .withHeader(map)   可以忽略不写 默认是Hs256编码
                .withClaim("userId",22)
                .withClaim("username","xiaoming")
                .withClaim("phone","1232565")
                .withExpiresAt(instance.getTime())//指定令牌的过期时间
                .sign(Algorithm.HMAC256("dfvdfgg")); //签名
        System.out.println(token);
    }

    @Test
    public void test(){
        //创建验证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("dfvdfgg")).build();

        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwaG9uZSI6IjEyMzI1NjUiLCJleHAiOjE2MjA1MzUwMTAsInVzZXJJZCI6MjIsInVzZXJuYW1lIjoieGlhb21pbmcifQ.cfb-dwHO4HanE9UbzaQTxQoXRlB8II9E8FR1UnIb6rc");

        System.out.println(verify.getClaims().get("userId").asInt());
        System.out.println(verify.getClaim("username").asString());
        System.out.println(verify.getClaim("phone").asString());
    }
}

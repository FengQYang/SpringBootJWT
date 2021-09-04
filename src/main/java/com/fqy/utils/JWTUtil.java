package com.fqy.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtil {

    private static  final  String SING = "!@#D@jkhhjkhjk";

    /**
     * 生成token  header.payload.sing
     */
    public static String getToken(Map<String , String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);  //默认7天
        JWTCreator.Builder builder = JWT.create();
       map.forEach((k,v)->{
           builder.withClaim(k,v);
       });
       String token = builder.withExpiresAt(instance.getTime())//指定过期时间
                .sign(Algorithm.HMAC256(SING));
       return token ;
    }

    /**
     * 验证token  合法性
     */
    public static DecodedJWT verify(String token){
         return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

//    /**
//     * 获取token信息方法
//     */
//    public static DecodedJWT getDecodedJWT(String token){
//        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
//        return verify ;
//    }
}

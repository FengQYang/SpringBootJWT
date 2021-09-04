package com.fqy.Controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fqy.pojo.User;
import com.fqy.service.UserService;
import com.fqy.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserController {

    @Resource
    private  UserService userService;

    @GetMapping("/user/login")
    public Map<String , Object> login(User user){
        log.info("用户名：[{}]",user.getName());
        log.info("密码：[{}]",user.getPassword());
        HashMap<String, Object> map = new HashMap<>();
        try {
            User userDB = userService.login(user);
            HashMap<String, String> payload = new HashMap<>();
            payload.put("id",userDB.getId());
            payload.put("name",userDB.getName());
            //生成JWT的令牌
            String token = JWTUtil.getToken(payload);
            map.put("state",true);
            map.put("msg","认证成功");
            map.put("token",token); //响应token
        }catch (Exception e){
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
         return map;
    }

    @GetMapping("/test")
    public String Tst(){
        return  "SUCCESS!";
    }
/*    @PostMapping("/user/test")
    public Map<String , Object> test(String token){
        HashMap<String, Object> map = new HashMap<>();
        log.info("当前token为： [{}]",token);
        try {
           // DecodedJWT verify = JWTUtil.verify(token); //验证令牌
            map.put("state",true);
            map.put("msg","请求成功！");
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            map.put("msg","无效签名!");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","token过期!");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg","算法不一致!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","token无效!");
        }
        map.put("state",false);
        return map;
    }*/
}

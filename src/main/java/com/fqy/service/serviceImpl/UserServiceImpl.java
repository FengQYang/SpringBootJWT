package com.fqy.service.serviceImpl;

import com.fqy.mapper.UserMapper;
import com.fqy.pojo.User;
import com.fqy.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        User userDB = userMapper.login(user);
        if (userDB != null){
            return userDB;
        }
        throw  new RuntimeException("认证失败!");
    }
}

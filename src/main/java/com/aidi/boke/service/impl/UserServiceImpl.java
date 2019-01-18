package com.aidi.boke.service.impl;

import com.aidi.boke.dao.UserMapper;
import com.aidi.boke.domain.User;
import com.aidi.boke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserMapper userMapper;

    public User selectUserByName(String name) {
        return userMapper.selectUserByName(name);

    }

    @Override
    public Integer saveUser(User user) {
       return userMapper.saveUser(user);
    }

    @Override
    public User loginUser(User user) {
        return userMapper.loginUser(user);
    }


}

package com.aishangrun.aishangrun.service.impl;

import com.aishangrun.aishangrun.entity.UserEntity;
import com.aishangrun.aishangrun.mapper.UserMapper;
import com.aishangrun.aishangrun.service.agent.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUserPhone(Map map) {
        return userMapper.addUserPhone(map);
    }

    @Override
    public List<UserEntity> selectUserList(Map map) {
        return userMapper.selectUserList(map);
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUser(UserEntity user) {
        return userMapper.deleteUser(user);
    }

    @Override
    public UserEntity selectUserPhone(Map map) {
        return userMapper.selectUserPhone(map);
    }
}

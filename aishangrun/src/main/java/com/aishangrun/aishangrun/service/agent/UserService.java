package com.aishangrun.aishangrun.service.agent;

import com.aishangrun.aishangrun.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    int addUserPhone(Map map);

    List<UserEntity> selectUserList(Map map);

    UserEntity updateUser(UserEntity user);

    int deleteUser(UserEntity user);

    //通过电话号码查询用户
    UserEntity selectUserPhone(Map map);

}

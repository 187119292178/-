package com.aishangrun.aishangrun.mapper;

import com.aishangrun.aishangrun.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    //添加个用户
    int addUserPhone(Map map);

    //查询用户list 列表
    List<UserEntity> selectUserList(Map map);

    //通过电话号码查询用户
    UserEntity selectUserPhone(Map map);

    // 修改用户信息
    UserEntity updateUser(UserEntity user);

    //逻辑删除个用户
    int deleteUser(UserEntity user);
}

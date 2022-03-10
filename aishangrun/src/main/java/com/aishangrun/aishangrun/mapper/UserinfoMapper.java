package com.aishangrun.aishangrun.mapper;

import com.aishangrun.aishangrun.entity.UserinfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserinfoMapper {
    //添加个用户信息
    int addUserinfoPhone(Map map);

    //查询用户信息list 列表
    List<UserinfoEntity> selectUserinfoList(Map map);

    // 修改用户个人信息
    int updateUserinfo(UserinfoEntity userinfo);

    //逻辑删除个用户信息
    int deleteUserinfo(UserinfoEntity userinfo);

    //通过电话号码查询用户
    UserinfoEntity selectUserinfoPhone(Map map);

}

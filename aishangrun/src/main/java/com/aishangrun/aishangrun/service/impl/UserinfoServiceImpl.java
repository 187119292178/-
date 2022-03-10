package com.aishangrun.aishangrun.service.impl;

import com.aishangrun.aishangrun.entity.UserinfoEntity;
import com.aishangrun.aishangrun.mapper.UserinfoMapper;
import com.aishangrun.aishangrun.service.agent.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserinfoServiceImpl implements UserinfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Override
    public int addUserinfoPhone(Map map) {
        return userinfoMapper.addUserinfoPhone(map);
    }

    @Override
    public List<UserinfoEntity> selectUserinfoList(Map map) {
        return userinfoMapper.selectUserinfoList(map);
    }

    @Override
    public int updateUserinfo(UserinfoEntity userinfo) {
        return userinfoMapper.updateUserinfo(userinfo);
    }

    @Override
    public int deleteUserinfo(UserinfoEntity userinfo) {
        return userinfoMapper.deleteUserinfo(userinfo);
    }

    @Override
    public UserinfoEntity selectUserinfoPhone(Map map) {
        return userinfoMapper.selectUserinfoPhone(map);
    }
}

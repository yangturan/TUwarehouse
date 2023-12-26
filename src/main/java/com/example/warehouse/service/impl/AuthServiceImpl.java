package com.example.warehouse.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.warehouse.mapper.AuthMapper;
import com.example.warehouse.pojo.Auth.AuthInfo;
import com.example.warehouse.service.AuthSerivce;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthServiceImpl implements AuthSerivce {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<AuthInfo> getAuthTree(int userid) {
        String testAuthTree = stringRedisTemplate.opsForValue().get(userid+"AuthTree");
        if(StringUtils.hasText(testAuthTree)){
            return JSON.parseArray(testAuthTree,AuthInfo.class);
        }
        List<AuthInfo> authTree = authMapper.getAuthTree(userid);
        //将list集合转化为菜单树
        List<AuthInfo> authInfos = allAuthToAuthTree(authTree, 0);
        stringRedisTemplate.opsForValue().set(userid+"AuthTree",JSON.toJSONString(authInfos));
        return authInfos;
    }

    //将所有权限(菜单)转成权限(菜单)树的递归算法
    private List<AuthInfo> allAuthToAuthTree(List<AuthInfo> allAuthList, int parentId){
        //获取父权限(菜单)id为参数parentId的所有权限(菜单)
        //【parentId最初为0,即最初查的是所有一级权限(菜单)】
        List<AuthInfo> authList = new ArrayList<>();
        for (AuthInfo auth : allAuthList) {
            if(auth.getParentId()==parentId){
                authList.add(auth);
            }
        }
        //查询List<Auth> authList中每个权限(菜单)的所有子级权限(菜单)
        for (AuthInfo auth : authList) {
            List<AuthInfo> childAuthList = allAuthToAuthTree(allAuthList, auth.getAuthId());
            auth.setChildAuth(childAuthList);
        }
        return authList;
    }
}

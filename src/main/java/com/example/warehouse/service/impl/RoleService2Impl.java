package com.example.warehouse.service.impl;

import com.example.warehouse.mapper.RoleMapper;
import com.example.warehouse.pojo.Role.Role;
import com.example.warehouse.service.RoleService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "com.example.warehouse.service.impl.RoleService2Impl")
public class RoleService2Impl implements RoleService2{

    @Autowired
    private RoleMapper roleMapper;

    @Cacheable("allRole")
    @Override
    //获取全部角色方法
    public List<Role> getAllRole() {
        List<Role> allRole = roleMapper.getAllRole();
        return allRole;
    }
}

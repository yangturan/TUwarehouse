package com.example.warehouse.service.impl;

import com.example.warehouse.Exception.BusinessException;
import com.example.warehouse.mapper.RoleMapper;
import com.example.warehouse.pojo.Auth.AuthInfo;
import com.example.warehouse.pojo.Auth.UpdateRoleAuthUtil;
import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.Role.Role;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result rolePage(Role role, Page page) {
        int allRoleCount = roleMapper.getAllRoleCount(role);
        page.setTotalNum(allRoleCount);
        page.pageCount();
        page.indexStart();
        List<Role> roles = roleMapper.rolePage(role, page);
        page.setResultList(roles);
        if(!roles.isEmpty()){
            return Result.ok(page);
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"当前没有角色信息，请添加");
        }
    }

    @Override
    public Result addRole(Role role) {
        int i = roleMapper.addRole(role);
        if(i>0){
            return Result.ok("添加成功！");
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"插入失败，系统忙！");
        }
    }

    @Override
    public Result updateRoleState(Role role) {
            int i = roleMapper.updateRoleState(role);
            if (i > 0) {
                return Result.ok("修改成功！");
            } else {
                return Result.err(Result.CODE_ERR_BUSINESS, "修改失败，系统忙");
            }
        }

    @Override
    public Result deleteRole(int id) {
        Integer integer = roleMapper.deleteRole(id);
        if(integer>0){
            return Result.ok("删除成功");
        }else {
            return Result.err(Result.CODE_ERR_BUSINESS,"删除错误，系统忙");
        }
    }

    @Override
    public Result updateRoleDesc(Role role) {
        int i = roleMapper.updateRoleDesc(role);
        if(i>0){
            return Result.ok("修改成功！");
        }else{
            return Result.err(Result.CODE_ERR_UNLOGINED,"系统忙");
        }
    }

    @Override
    public Result getRoleAuth(int id) {
        List<Integer> roleAuth=roleMapper.getRoleAuth(id);
        return Result.ok(roleAuth);
    }

    @Override
    public Result updateRoleAuth(UpdateRoleAuthUtil updateRoleAuthUtil) {
        roleMapper.deleteRoleAuth(updateRoleAuthUtil);
        int i = roleMapper.addRoleAuth(updateRoleAuthUtil);
        if (i > 0) {
            return Result.ok("修改成功！");
        } else {
            return Result.err(Result.CODE_ERR_BUSINESS,"系统忙，请稍后");
        }
    }
}

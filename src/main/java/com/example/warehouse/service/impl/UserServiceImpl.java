package com.example.warehouse.service.impl;

import com.example.warehouse.mapper.UserMapper;
import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.Role.Role;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.pojo.users.User;
import com.example.warehouse.service.UserService;
import com.example.warehouse.utils.DigestUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Page getUsers(User user,Page page) {
        int totalNum = userMapper.getTotalNum(user);
        page.setTotalNum(totalNum);
        page.pageCount();
        page.indexStart();
        List<User> users = userMapper.getUsers(user, page);
        page.setResultList(users);
        return page;
    }

    @Override
    public Result addUser(User user) {
        User user1 = userMapper.queryHaveByUserCode(user.getUserCode());
        if(user1!=null){
            return Result.err(Result.CODE_ERR_BUSINESS,"该用户名已存在");
        }else {
            int i = userMapper.addUser(user);
            if(i>0){
                return Result.ok("添加成功！");
            }else{
                return Result.err(Result.CODE_ERR_BUSINESS,"添加失败，系统忙");
            }
        }
    }

    @Override
    public Result updateState(User user) {
        int i = userMapper.updateState(user);
        if(i>0){
            return Result.ok("修改成功！");
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"修改失败，系统忙");
        }
    }

    @Override
    public List<Role> getUserRole(int id) {
        List<Role> userRole = userMapper.getUserRole(id);
        return userRole;
    }

    @Override
    public List<Integer> getRoleIdByRoleName(List strings,Integer userId) {
            List<Integer> roleIdByRoleName = userMapper.getRoleIdByRoleName(strings);
            stringRedisTemplate.opsForValue().set(userId + "AssignIdList", String.valueOf(roleIdByRoleName), 3600, TimeUnit.SECONDS);
            return roleIdByRoleName;
    }

    @Override
    public int assignRole(int userId, List<Integer> roleIdByRoleName) {
        userMapper.deleteAllRoleByUserId(userId);
        return userMapper.insertByRoleId(userId,roleIdByRoleName);
    }

    @Override
    public Result deleteUser(int userId,int userid1) {
        int i = userMapper.selectRoleOkDeleteUser(userid1);
        if(i>0) {
            Integer integer = userMapper.deleteUser(userId);
            if (integer == 1) {
                return Result.ok("删除成功！");
            } else {
                return Result.err(Result.CODE_ERR_BUSINESS, "删除失败，系统忙");
            }
        }else{
            return Result.err(Result.CODE_ERR_SYS,"你没有权限删除用户");
        }
    }

    @Override
    public Result deleteManyUser(int[] ints,int userid) {
        int i = userMapper.selectRoleOkDeleteUser(userid);
        if(userid>0) {
            Integer integer = userMapper.deleteMantUser(ints);
            if (integer > 0) {
                return Result.ok("删除成功!");
            } else {
                return Result.err(Result.CODE_ERR_BUSINESS, "删除失败!");
            }
        }else{
            return Result.err(Result.CODE_ERR_SYS,"你没有权限删除用户");
        }
    }

    @Override
    public Result updateUserName(User user,int userid) {
        int i1 = userMapper.selectRoleOkDeleteUser(userid);
        if(i1>0) {
            int i = userMapper.updateUserName(user);
            if (i > 0) {
                return Result.ok("修改成功！");
            } else {
                return Result.err(Result.CODE_ERR_BUSINESS, "修改失败，系统忙");
                //你没有进行用户权限验证，下次补上哦！！
            }
        }else{
            return Result.err(Result.CODE_ERR_SYS,"你没有权限修改用户");
        }
    }

    @Override
    public Result updatePwd(int id) {
        String s = DigestUtil.hmacSign("123456");
        int i = userMapper.updatePwd(id,s);
        if(i>0){
            return Result.ok("已重置为123456");
        }else{
            return Result.err(Result.CODE_ERR_SYS,"无法重置");
        }
    }

}

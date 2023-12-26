package com.example.warehouse.service;

import com.example.warehouse.mapper.UserMapper;
import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.Role.Role;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.pojo.users.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    Page getUsers(User user,Page page);

    Result addUser(User user);

    Result updateState(User user);

    List<Role> getUserRole(int id);

    List<Integer> getRoleIdByRoleName(List strings,Integer userId);

    int assignRole(int userId, List<Integer> roleIdByRoleName);

    Result deleteUser(int userId,int userId1);

    Result deleteManyUser(int[] ints,int userid);

    Result updateUserName(User user,int userid);

    Result updatePwd(int id);

}

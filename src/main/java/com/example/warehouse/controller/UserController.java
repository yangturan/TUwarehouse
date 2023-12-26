package com.example.warehouse.controller;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.Role.AssignRole;
import com.example.warehouse.pojo.Role.Role;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.pojo.users.CurrentUser;
import com.example.warehouse.pojo.users.User;
import com.example.warehouse.service.UserService;
import com.example.warehouse.utils.DigestUtil;
import com.example.warehouse.utils.TokenUtils;
import com.example.warehouse.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @RequestMapping("/user-list")
    @ResponseBody
    public Result pageQuery(User user, Page page){
        Page page1 = userService.getUsers(user, page);
        return Result.ok(page1);
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public Result addUser(@RequestBody User user, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        String s = DigestUtil.hmacSign(user.getUserPwd());
        user.setUserPwd(s);
        user.setCreateBy(userId);
        return userService.addUser(user);
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public Result update(@RequestBody User user){
        return userService.updateState(user);
    }

    @RequestMapping("/user-role-list/{id}")
    @ResponseBody
    public Result getUserRole(@PathVariable("id") int id){
        List<Role> userRole = userService.getUserRole(id);
        return Result.ok(userRole);
    }

    //修改角色方法
    @RequestMapping("/assignRole")
    @ResponseBody
    public Result updateUserRole(@RequestBody AssignRole assignRole){
        int userId = assignRole.getUserId();
        List<String> roleCheckList = assignRole.getRoleCheckList();
        List<Integer> roleIdByRoleName = userService.getRoleIdByRoleName(roleCheckList,userId);
        userService.assignRole(userId,roleIdByRoleName);
        return Result.ok("修改成功！");
    }

    @RequestMapping("/deleteUser/{id}")
    @ResponseBody
    public Result deleteUser(@PathVariable("id") int userId,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId1 = currentUser.getUserId();
        return userService.deleteUser(userId,userId1);
    }

    @RequestMapping("/deleteUserList")
    @ResponseBody
    public Result deleteManyUser(@RequestBody String[] strings,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        int [] ints=new int[strings.length];
        for (int i=0;i<strings.length;i++) {
            ints[i]=Integer.parseInt(strings[i]);
        }
        return userService.deleteManyUser(ints,userId);
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public Result updateUserName(@RequestBody User user,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        userService.updateUserName(user,userId);
        return new Result();
    }

    @RequestMapping("/updatePwd/{id}")
    @ResponseBody
    public Result updatePwd(@PathVariable("id") int id){
        return userService.updatePwd(id);
    }

}

package com.example.warehouse.controller;

import com.example.warehouse.pojo.Auth.UpdateRoleAuthUtil;
import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.Role.Role;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.RoleService;
import com.example.warehouse.service.RoleService2;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")

public class RoleController {

    @Autowired
    private RoleService roleService;

    @Resource
    private RoleService2 roleService2;

    @RequestMapping("/role-list")
    @ResponseBody
    public Result getAllRole(){
        List<Role> allRole = roleService2.getAllRole();
        if(allRole.size()>0){
            return Result.ok(allRole);
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"你没有任何角色");
        }
    }

    @RequestMapping("/role-page-list")
    @ResponseBody
    public Result rolePage(Role role, Page page){
        return roleService.rolePage(role,page);
    }

    @RequestMapping("/role-add")
    @ResponseBody
    public Result addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @RequestMapping("/role-state-update")
    @ResponseBody
    public Result updateRoleState(@RequestBody Role role){
        return roleService.updateRoleState(role);
    }

    @RequestMapping("/role-delete/{id}")
    @ResponseBody
    public Result deleteRole(@PathVariable("id") int id){
        return roleService.deleteRole(id);
    }

    @RequestMapping("/role-update")
    @ResponseBody
    public Result updateRoleDesc(@RequestBody Role role){
        return roleService.updateRoleDesc(role);
    }

    @RequestMapping("/role-auth")
    @ResponseBody
    public Result getRoleAuth(int roleId){
        return roleService.getRoleAuth(roleId);
    }

    @RequestMapping("/auth-grant")
    @ResponseBody
    public Result updateRoleAuth(@RequestBody UpdateRoleAuthUtil updateRoleAuthUtil){
        return roleService.updateRoleAuth(updateRoleAuthUtil);
    }
}

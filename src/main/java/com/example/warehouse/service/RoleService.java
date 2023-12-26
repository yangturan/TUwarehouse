package com.example.warehouse.service;

import com.example.warehouse.pojo.Auth.UpdateRoleAuthUtil;
import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.Role.Role;
import com.example.warehouse.pojo.result.Result;

import java.util.List;

public interface RoleService {

    Result rolePage(Role role, Page page);

    Result addRole(Role role);

    Result updateRoleState(Role role);

    Result deleteRole(int id);

    Result updateRoleDesc(Role role);

    Result getRoleAuth(int id);

    Result updateRoleAuth(UpdateRoleAuthUtil updateRoleAuthUtil);
}

package com.example.warehouse.mapper;

import com.example.warehouse.pojo.Auth.AuthInfo;
import com.example.warehouse.pojo.Auth.UpdateRoleAuthUtil;
import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.Role.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<Role> getAllRole();

    int getAllRoleCount(@Param("role") Role role);

    List<Role> rolePage(@Param("role") Role role,@Param("page") Page page);

    int addRole(Role role);

    int updateRoleState(Role role);

    Integer deleteRole(int id);

    int updateRoleDesc(Role role);

    List<Integer> getRoleAuth(int id);

    int deleteRoleAuth(UpdateRoleAuthUtil updateRoleAuthUtil);

    int addRoleAuth(UpdateRoleAuthUtil updateRoleAuthUtil);
}

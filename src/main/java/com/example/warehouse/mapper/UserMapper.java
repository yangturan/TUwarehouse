package com.example.warehouse.mapper;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.Role.Role;
import com.example.warehouse.pojo.users.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    //查询符合要求的用户总共有多少条
    int getTotalNum(User user);

    //查询出当前分页需要显示的数据
    List<User> getUsers(@Param("user") User user,@Param("page") Page page);

    //添加用户时根据用户昵称查询是否已存在该用户
    User queryHaveByUserCode(String userCode);

    //添加用户方法
    int addUser(User user);

    int updateState(User user);

    List<Role> getUserRole(int id);

    List<Integer> getRoleIdByRoleName(List strings);

    void deleteAllRoleByUserId(int userId);

    Integer insertByRoleId(@Param("userId") int userId, @Param("roleIdByRoleName") List<Integer> roleIdByRoleName);

    //根据id删除用户
    Integer deleteUser(int userId);

    Integer deleteMantUser(int[] ints);

    int updateUserName(User user);

    int selectRoleOkDeleteUser(int userid1);

    int updatePwd(int id,String s);

}

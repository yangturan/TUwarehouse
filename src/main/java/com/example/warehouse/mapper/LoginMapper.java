package com.example.warehouse.mapper;

import com.example.warehouse.pojo.users.LoginUser;
import com.example.warehouse.pojo.users.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select * from user_info where user_code=#{userCode} and is_delete='0'")
    User login(String userCode);
}

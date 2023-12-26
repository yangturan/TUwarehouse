package com.example.warehouse.mapper;

import com.example.warehouse.pojo.Auth.AuthInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuthMapper {
    @Select("select s3.* from user_role s1,role_auth s2,auth_info s3 where s1.user_id=#{userid} and s1.role_id=s2.role_id and s2.auth_id=s3.auth_id and s3.auth_state=1 and s3.auth_type!=3")
    List<AuthInfo> getAuthTree(int userid);
}

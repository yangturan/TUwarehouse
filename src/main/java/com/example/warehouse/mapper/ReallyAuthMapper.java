package com.example.warehouse.mapper;

import com.example.warehouse.pojo.Auth.AuthInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReallyAuthMapper {
    List<AuthInfo> getAllAuth();
}

package com.example.warehouse.service.impl;

import com.example.warehouse.mapper.ReallyAuthMapper;
import com.example.warehouse.pojo.Auth.AuthInfo;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.ReallyAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReallyAuthServiceImpl implements ReallyAuthService {

    @Autowired
    private ReallyAuthMapper reallyAuthMapper;

    @Override
    public Result getAllAuth() {
        List<AuthInfo> allAuth = reallyAuthMapper.getAllAuth();
        if(allAuth.size()>0){
            return Result.ok(allAuth);
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"你没有设置任何权限");
        }
    }
}

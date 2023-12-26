package com.example.warehouse.service.impl;

import com.example.warehouse.mapper.LoginMapper;
import com.example.warehouse.pojo.users.LoginUser;
import com.example.warehouse.pojo.users.User;
import com.example.warehouse.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public User login(String userCode) {
        return loginMapper.login(userCode);
    }
}

package com.example.warehouse.service;

import com.example.warehouse.pojo.users.LoginUser;
import com.example.warehouse.pojo.users.User;

public interface LoginService {
    User login(String userCode);
}

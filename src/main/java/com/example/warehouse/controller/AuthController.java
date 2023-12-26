package com.example.warehouse.controller;

import com.example.warehouse.pojo.Auth.AuthInfo;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.pojo.users.CurrentUser;
import com.example.warehouse.service.AuthSerivce;
import com.example.warehouse.utils.TokenUtils;
import com.example.warehouse.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private AuthSerivce authSerivce;

    @Autowired
    private TokenUtils tokenUtils;

    @ResponseBody
    @RequestMapping("/curr-user")
    public Result getUser(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        return Result.ok(currentUser);
    }

    @ResponseBody
    @RequestMapping("/user/auth-list")
    public Result getAuthTree(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        List<AuthInfo> authTree = authSerivce.getAuthTree(currentUser.getUserId());
        return Result.ok(authTree);
    }
}

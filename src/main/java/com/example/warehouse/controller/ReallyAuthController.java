package com.example.warehouse.controller;

import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.ReallyAuthService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class ReallyAuthController {

    @Resource
    private ReallyAuthService reallyAuthService;

    @RequestMapping("/auth-tree")
    public Result getAllAuth(){
        return reallyAuthService.getAllAuth();
    }
}

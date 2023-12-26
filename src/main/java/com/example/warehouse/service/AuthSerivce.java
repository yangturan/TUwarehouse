package com.example.warehouse.service;

import com.example.warehouse.pojo.Auth.AuthInfo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthSerivce {
    List<AuthInfo> getAuthTree(int userid);
}

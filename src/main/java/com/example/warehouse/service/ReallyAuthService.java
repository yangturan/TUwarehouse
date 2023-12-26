package com.example.warehouse.service;

import com.example.warehouse.mapper.ReallyAuthMapper;
import com.example.warehouse.pojo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;

public interface ReallyAuthService {

    Result getAllAuth();
}

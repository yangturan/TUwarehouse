package com.example.warehouse.controller;

import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class EchartsController {

    @Autowired
    private EchartsService echartsService;

    @RequestMapping("/store-invent")
    public Result echarts(){
        return echartsService.echarts();
    }
}

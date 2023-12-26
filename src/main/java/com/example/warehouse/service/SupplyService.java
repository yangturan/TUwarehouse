package com.example.warehouse.service;

import com.example.warehouse.pojo.product.Supply;
import com.example.warehouse.pojo.result.Result;

/**
* @author 32966
* @description 针对表【supply(供货商)】的数据库操作Service
* @createDate 2023-12-14 11:14:36
*/
public interface SupplyService{

    Result getAllSupply();
}

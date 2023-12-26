package com.example.warehouse.service;

import com.example.warehouse.pojo.product.Unit;
import com.example.warehouse.pojo.result.Result;

/**
* @author 32966
* @description 针对表【unit(规格单位表)】的数据库操作Service
* @createDate 2023-12-17 15:22:33
*/
public interface UnitService{

    Result getAllUnit();
}

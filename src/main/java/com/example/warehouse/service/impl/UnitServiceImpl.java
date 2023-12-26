package com.example.warehouse.service.impl;


import com.example.warehouse.pojo.product.Unit;
import com.example.warehouse.service.UnitService;
import com.example.warehouse.mapper.UnitMapper;
import com.example.warehouse.pojo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 32966
* @description 针对表【unit(规格单位表)】的数据库操作Service实现
* @createDate 2023-12-17 15:22:33
*/
@Service
@Transactional
public class UnitServiceImpl implements UnitService{

    @Autowired
    private UnitMapper unitMapper;
    @Override
    public Result getAllUnit() {
        List<Unit> units=unitMapper.getAllUnit();
        if(units.size()>0){
            return Result.ok(units);
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"你没有任何单位");
        }
    }
}





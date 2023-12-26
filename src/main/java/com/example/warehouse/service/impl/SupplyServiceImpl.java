package com.example.warehouse.service.impl;

import com.example.warehouse.mapper.SupplyMapper;
import com.example.warehouse.pojo.product.Supply;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.SupplyService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 32966
* @description 针对表【supply(供货商)】的数据库操作Service实现
* @createDate 2023-12-14 11:14:36
*/
@Service
@CacheConfig
@Transactional
public class SupplyServiceImpl implements SupplyService{

    @Resource
    private SupplyMapper supplyMapper;
    @Override
    @Cacheable(cacheNames = "allsupply")
    public Result getAllSupply() {
        List<Supply> supplys=supplyMapper.getAllSupply();
        if(supplys.size()>0){
            return Result.ok(supplys);
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"你没有任何可以联系的公司");
        }
    }
}





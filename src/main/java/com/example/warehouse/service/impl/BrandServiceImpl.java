package com.example.warehouse.service.impl;

import com.example.warehouse.pojo.product.Brand;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.BrandService;
import com.example.warehouse.mapper.BrandMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 32966
* @description 针对表【brand(品牌)】的数据库操作Service实现
* @createDate 2023-12-14 11:04:02
*/
@Service
@CacheConfig
@Transactional
public class BrandServiceImpl implements BrandService{

    @Resource
    private BrandMapper brandMapper;
    @Override
    @Cacheable(cacheNames = "allbrand")
    public Result getAllBrand() {
        List<Brand> brands=brandMapper.getAllBrand();
        if(brands.size()>0){
            return Result.ok(brands);
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"你没有任何品牌可以联系");
        }
    }
}





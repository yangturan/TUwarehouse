package com.example.warehouse.mapper;

import com.example.warehouse.pojo.product.Brand;

import java.util.List;

/**
* @author 32966
* @description 针对表【brand(品牌)】的数据库操作Mapper
* @createDate 2023-12-14 11:04:02
* @Entity com.example.warehouse.pojo.product.Brand
*/
public interface BrandMapper {

    List<Brand> getAllBrand();
}





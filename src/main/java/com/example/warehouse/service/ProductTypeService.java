package com.example.warehouse.service;

import com.example.warehouse.pojo.product.ProductType;
import com.example.warehouse.pojo.result.Result;

/**
* @author 32966
* @description 针对表【product_type(商品分类表)】的数据库操作Service
* @createDate 2023-12-21 10:23:05
*/
public interface ProductTypeService {

    Result getAllType();

    Result validationCode(ProductType productType);

    Result addType(ProductType productType);

    Result typeUpdate(ProductType productType);

    int typeDelete(int typeId);

    int[] selectChild(int typeId);

    int typesDelete(int[] is);
}

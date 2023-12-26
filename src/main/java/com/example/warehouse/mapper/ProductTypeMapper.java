package com.example.warehouse.mapper;

import com.example.warehouse.pojo.product.ProductType;
import com.example.warehouse.pojo.result.Result;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 32966
* @description 针对表【product_type(商品分类表)】的数据库操作Mapper
* @createDate 2023-12-21 10:23:05
* @Entity com.example.warehouse.pojo.product.ProductType
*/
@Mapper
public interface ProductTypeMapper {

    List<ProductType> getAllType();

    List<ProductType> validationCode(ProductType productType);

    int addType(ProductType productType);

    List<ProductType> validationName(ProductType productType);

    int typeUpdate(ProductType productType);

    int typeDelete(int typeId);

    int[] selectChild(int typeId);

    int typesDelete(int[] is);
}





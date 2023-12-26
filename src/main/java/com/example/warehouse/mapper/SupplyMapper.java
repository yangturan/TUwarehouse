package com.example.warehouse.mapper;

import com.example.warehouse.pojo.product.Supply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 32966
* @description 针对表【supply(供货商)】的数据库操作Mapper
* @createDate 2023-12-14 11:14:36
* @Entity com.example.warehouse.pojo.product.Supply
*/
@Mapper
public interface SupplyMapper{

    List<Supply> getAllSupply();
}





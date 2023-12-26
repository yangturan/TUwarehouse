package com.example.warehouse.mapper;


import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.BuyList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 32966
* @description 针对表【buy_list(采购单)】的数据库操作Mapper
* @createDate 2023-12-22 14:37:46
* @Entity com.example.warehouse.pojo.product.BuyList
*/
@Mapper
public interface BuyListMapper {

    int buy(BuyList buyList);

    List<BuyList> buypage(@Param("page")Page page, @Param("buyList")BuyList buyList);

    int getAllBuyCount(@Param("buyList") BuyList buyList);

    int updateBuy(BuyList buyList);
}





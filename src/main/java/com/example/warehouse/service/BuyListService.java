package com.example.warehouse.service;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.BuyList;
import com.example.warehouse.pojo.result.Result;

/**
* @author 32966
* @description 针对表【buy_list(采购单)】的数据库操作Service
* @createDate 2023-12-22 14:37:46
*/
public interface BuyListService{

    int buy(BuyList buyList);


    Page buypage(Page page, BuyList buyList);

    int updateBuy(BuyList buyList);
}

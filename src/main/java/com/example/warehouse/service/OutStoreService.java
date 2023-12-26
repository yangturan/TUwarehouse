package com.example.warehouse.service;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.OutStore;
import com.example.warehouse.pojo.product.Product;
import com.example.warehouse.pojo.result.Result;

import java.util.List;

/**
* @author 32966
* @description 针对表【out_store(出库单)】的数据库操作Service
* @createDate 2023-12-23 14:57:03
*/
public interface OutStoreService{

    int makeout(Product product,String header);

    List<OutStore> outpage(OutStore outStore, Page page);

    Result sureOut(OutStore outStore);
}

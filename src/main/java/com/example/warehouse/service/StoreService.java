package com.example.warehouse.service;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.Store;
import com.example.warehouse.pojo.result.Result;

import java.util.List;


/**
* @author 32966
* @description 针对表【store(仓库表)】的数据库操作Service
* @createDate 2023-12-26 10:50:01
*/
public interface StoreService {

    Result getAllStore();

    List<Store> getStorePage(Store store, Page page);

    Result validation(String storeNum);

    Result makeStore(Store store);

    Result deleteStore(int id);

    Result updateStore(Store store);
}

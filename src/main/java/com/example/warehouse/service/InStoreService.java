package com.example.warehouse.service;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.InStore;
import com.example.warehouse.pojo.result.Result;

/**
* @author 32966
* @description 针对表【in_store(入库单)】的数据库操作Service
* @createDate 2023-12-24 15:45:52
*/
public interface InStoreService{

    Result inStorePage(Page page, InStore inStore);

    Result buyListToInStore(InStore instore,String token);

    Result sureInstore(InStore inStore);
}

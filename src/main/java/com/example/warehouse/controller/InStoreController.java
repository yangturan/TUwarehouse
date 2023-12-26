package com.example.warehouse.controller;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.InStore;
import com.example.warehouse.service.InStoreService;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/instore")
@RestController
public class InStoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private InStoreService inStoreService;

    @RequestMapping("/store-list")
    public Result getAllStore(){
        return storeService.getAllStore();
    }
//    /instore-page-list?storeId=1&productName=&startTime=&
//    endTime=&pageSize=5&pageNum=1&totalNum=0&_t=1703403511297
    @RequestMapping("/instore-page-list")
    public Result inStorePage(Page page, InStore inStore){
        return inStoreService.inStorePage(page,inStore);
    }

    @RequestMapping("/instore-confirm")
    public Result sureInStore(@RequestBody InStore inStore){
        return inStoreService.sureInstore(inStore);
    }
}

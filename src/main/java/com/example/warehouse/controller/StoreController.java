package com.example.warehouse.controller;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.Store;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CacheConfig
@RequestMapping("/store")
public class StoreController {
//    http://localhost:3000/api/store/store-page-list?
//    storeName=&storeAddress=&concat=
//    &phone=&pageSize=5&pageNum=1&totalNum=0&_t=1703558899964
    @Autowired
    private StoreService storeService;
    @RequestMapping("/store-page-list")
    public Result getStorePage(Store store, Page page){
        List<Store> list=storeService.getStorePage(store,page);
        if(list.size()>0){
            page.setResultList(list);
            return Result.ok(page);
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"没有任何仓库");
        }
    }

    @RequestMapping("/store-num-check")
    public Result validation(String storeNum){
        return storeService.validation(storeNum);
    }

    @RequestMapping("/store-add")
    @CacheEvict("allstore")
    public Result makeStore(@RequestBody Store store){
        return storeService.makeStore(store);
    }

    @RequestMapping("/store-delete/{id}")
    public Result deleteStore(@PathVariable int id){
        return storeService.deleteStore(id);
    }

    @RequestMapping("/store-update")
    public Result updateStore(@RequestBody Store store){
        return storeService.updateStore(store);
    }
}

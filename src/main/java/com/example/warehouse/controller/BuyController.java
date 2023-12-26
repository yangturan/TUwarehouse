package com.example.warehouse.controller;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.BuyList;
import com.example.warehouse.pojo.product.InStore;
import com.example.warehouse.service.InStoreService;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.BuyListService;
import com.example.warehouse.service.StoreService;
import com.example.warehouse.utils.WarehouseConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/purchase")
@Transactional
@CacheConfig
public class BuyController {

    @Autowired
    private InStoreService inStoreService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private BuyListService buyListService;

    @RequestMapping("/purchase-add")
    @ResponseBody
    public Result Buy(@RequestBody BuyList buyList){
        int i=buyListService.buy(buyList);
        if(i>0){
            return Result.ok("添加成功");
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"采购失败!");
        }
    }

    @RequestMapping("/store-list")
    @ResponseBody
    @Cacheable("allstore")
    public Result getAllStore(){
        return storeService.getAllStore();
    }

    @RequestMapping("/purchase-page-list")
    @ResponseBody
    //purchase-page-list?storeId=1&startTime=&endTime=&productName=&
    // buyUser=&isIn=&pageSize=5&pageNum=1&totalNum=0&_t=1703230807364
    public Result buypage(Page page,BuyList buyList){
        Page page1=buyListService.buypage(page,buyList);
        if(page.getResultList().size()>0){
            return Result.ok(page1);
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"对不起，该条件下没有任何数据");
        }
    }

    @RequestMapping("/purchase-update")
    @ResponseBody
    public Result updateBuy(@RequestBody BuyList buyList){
        int i=buyListService.updateBuy(buyList);
        if(i>0){
            return Result.ok("修改成功！");
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"修改失败!");
        }
    }

    @RequestMapping("/in-warehouse-record-add")
    @ResponseBody
    public Result buyListToInStore(@RequestBody InStore instore, HttpServletRequest request){
        String token = request.getHeader(WarehouseConstants.HEADER_TOKEN_NAME);
        return inStoreService.buyListToInStore(instore,token);
    }


}

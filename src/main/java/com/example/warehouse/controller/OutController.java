package com.example.warehouse.controller;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.OutStore;
import com.example.warehouse.pojo.product.Product;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.OutStoreService;
import com.example.warehouse.service.StoreService;
import com.example.warehouse.utils.WarehouseConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

//出库单控制层
@Controller
@RestController
@RequestMapping("/outstore")
public class OutController {

    @Autowired
    private OutStoreService outStoreService;

    @Autowired
    private StoreService storeService;

    @RequestMapping("/outstore-add")
    public Result out(@RequestBody Product product, HttpServletRequest request){
        String header = request.getHeader(WarehouseConstants.HEADER_TOKEN_NAME);
        int i=outStoreService.makeout(product,header);
        if(i>0){
            return Result.ok("出库单生成成功！");
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"出库失败！");
        }
    }

    @RequestMapping("/store-list")
    public Result getstore(){
        return storeService.getAllStore();
    }

    //http://localhost:3000/api/outstore/outstore-page-list?
    // storeId=1&productName=&startTime=&endTime=
    // &isOut=&pageSize=5&pageNum=1&totalNum=0&_t=1703316895998
    @RequestMapping("/outstore-page-list")
    public Result outpage(OutStore outStore, Page page){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            if(outStore.getStartTime()!=null&&outStore.getStartTime()!="") {
                outStore.setStime(sdf.parse(outStore.getStartTime()));
            }
            if(outStore.getEndTime()!=null&&outStore.getEndTime()!="") {
                outStore.setEtime(sdf.parse(outStore.getEndTime()));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<OutStore> outs=outStoreService.outpage(outStore,page);
        page.setResultList(outs);
        if(outs.size()>0){
            return Result.ok(page);
        }else {
            return Result.err(Result.CODE_ERR_BUSINESS,"没有符合要求的出库单");
        }
    }

    @RequestMapping("/outstore-confirm")
    public Result sureOut(@RequestBody OutStore outStore){
        return outStoreService.sureOut(outStore);
    }
}

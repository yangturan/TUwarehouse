package com.example.warehouse.controller;

import com.example.warehouse.pojo.product.ProductType;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.ProductService;
import com.example.warehouse.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/productCategory")
@RestController
public class ProductSortController {

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/product-category-tree")
    public Result productTree(){
        return productTypeService.getAllType();
    }

    @RequestMapping("/verify-type-code")
    public Result validationCode(ProductType productType){
        return productTypeService.validationCode(productType);
    }

    @RequestMapping("/type-add")
    public Result addType(@RequestBody ProductType productType){
        return productTypeService.addType(productType);
    }

    @RequestMapping("/type-update")
    public Result typeUpdate(@RequestBody ProductType productType){
        return productTypeService.typeUpdate(productType);
    }

    @RequestMapping("/type-delete/{id}")
    public Result typeDelete(@PathVariable("id") int typeId){
        int[] is=productTypeService.selectChild(typeId);
        if(is.length>0){
            int i1=productTypeService.typesDelete(is);
            if(i1<=0){
                return Result.err(Result.CODE_ERR_BUSINESS,"未能删除子元素，已全部撤回");
            }
        }
        int i=productTypeService.typeDelete(typeId);
        if(i>0){
            return Result.ok("删除成功！");
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"删除失败，系统忙！");
        }
    }
}

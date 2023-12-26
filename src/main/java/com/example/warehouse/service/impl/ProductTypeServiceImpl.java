package com.example.warehouse.service.impl;

import com.example.warehouse.pojo.product.ProductType;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.ProductTypeService;
import com.example.warehouse.mapper.ProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* @author 32966
* @description 针对表【product_type(商品分类表)】的数据库操作Service实现
* @createDate 2023-12-21 10:23:05
*/
@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService{

    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Override
    public Result getAllType() {
        List<ProductType> allType = productTypeMapper.getAllType();
        //容纳父类型
        List<ProductType> parent=new ArrayList<>();
        for (ProductType productType : allType) {
            if(productType.getParentId().equals(0)){
                parent.add(productType);
            }
        }
        //装载子类型
        for (ProductType productType : parent) {
            Integer typeId = productType.getTypeId();
            productType.setChildProductCategory(new ArrayList<ProductType>());
            for (ProductType type : allType) {
                if(type.getParentId().equals(typeId)){
                    productType.getChildProductCategory().add(type);
                }
            }
        }
        if(allType.size()>0){
            return Result.ok(parent);
        }else {
            return Result.err(Result.CODE_ERR_BUSINESS,"你没有任何种类，请先添加");
        }
    }

    @Override
    public Result validationCode(ProductType productType) {
        List<ProductType> p1=productTypeMapper.validationCode(productType);
        return Result.ok(p1.size()==0);
    }

    @Override
    public Result addType(ProductType productType) {
        List<ProductType> p1=productTypeMapper.validationName(productType);
        if(p1.size()==0) {
            int i = productTypeMapper.addType(productType);
            if (i > 0) {
                return Result.ok("添加成功");
            } else {
                return Result.err(Result.CODE_ERR_BUSINESS, "添加失败，系统忙");
            }
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"该分类已存在");
        }
    }

    @Override
    public Result typeUpdate(ProductType productType) {
        int i=productTypeMapper.typeUpdate(productType);
        if(i>0){
            return Result.ok("修改成功！");
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"修改失败！");
        }
    }

    @Override
    public int typeDelete(int typeId) {
        return productTypeMapper.typeDelete(typeId);
    }

    @Override
    public int[] selectChild(int typeId) {
        return productTypeMapper.selectChild(typeId);
    }

    @Override
    public int typesDelete(int[] is) {
        return productTypeMapper.typesDelete(is);
    }
}





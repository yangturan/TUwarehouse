package com.example.warehouse.service.impl;

import com.example.warehouse.mapper.ProductMapper;
import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.Product;
import com.example.warehouse.pojo.product.ProductPageUtil;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Value("${file.access-path}")
    private String path;


    //分页查询商品
    @Override
    public Result ProductPage(Page page, ProductPageUtil productPageUtil) {
        int allRoleCount = productMapper.getAllProductCount(productPageUtil);
        page.setTotalNum(allRoleCount);
        page.pageCount();
        page.indexStart();
        Date date = new Date();
        Timestamp timestamp=new Timestamp(date.getTime());
        productPageUtil.setNow(timestamp);
        List<Product> products = productMapper.productPage(page, productPageUtil);
        page.setResultList(products);
        if (products.size()>0){
            return Result.ok(page);
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"没有任何商品满足条件");
        }
    }


    //改变商品上下架状态
    @Override
    public Result productStateChange(Product product) {
        int i=productMapper.productStateChange(product);
        if(i>0){
            return Result.ok("修改成功！");
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"系统忙，请稍后再试");
        }
    }

    @Override
    public Result addProduct(Product product) {
        String old=product.getImgs();
        product.setImgs(path.concat(old));
        int i = productMapper.addProduct(product);
        if(i>0){
            return Result.ok("添加成功！");
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"系统忙，请稍后再试！");
        }
    }

    @Override
    public Result deleteProducts(Integer[] ints) {
        int i=productMapper.deleteProducts(ints);
        if(i>0){
            return Result.ok("删除成功！");
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"系统忙，请稍后再试");
        }
    }

    @Override
    public Result updateProduct(Product product) {
        int i=productMapper.updateProduct(product);
        int i2=productMapper.updateBrand(product);
        int i3=productMapper.updatePlace(product);
        int i4=productMapper.updateStore(product);
        int i5=productMapper.updateSupply(product);
        int i6=productMapper.updateType(product);
        if(i>0&&i2>0&&i3>0&&i4>0&&i5>0&&i6>0){
            return Result.ok("修改成功！");
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"系统忙，请稍后再试！");
        }
    }

    @Override
    public Result deleteOne(int id) {
        int i=productMapper.deleteOne(id);
        if(i>0){
            return Result.ok("删除成功！");
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"删除失败！");
        }
    }
}


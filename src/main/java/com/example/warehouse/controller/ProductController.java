package com.example.warehouse.controller;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.Product;
import com.example.warehouse.pojo.product.ProductPageUtil;
import com.example.warehouse.service.*;
import com.example.warehouse.pojo.result.Result;
import io.netty.util.internal.ResourcesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@RequestMapping("/product")
@CacheConfig
@Controller
public class ProductController {
    //图片上传路径
    @Value("${file.upload-path}")
    private String imgpath;

    @Autowired
    private StoreService storeService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    //获取全部商品
    @RequestMapping("/store-list")
    @ResponseBody
    public Result getAllStore(){
        return storeService.getAllStore();
    }

    @RequestMapping("/brand-list")
    @ResponseBody
    public Result getAllBrand(){
        return brandService.getAllBrand();
    }

    @RequestMapping("/supply-list")
    @ResponseBody
    public Result getAllSupply(){
        return supplyService.getAllSupply();
    }

    @RequestMapping("/place-list")
    @ResponseBody
    public Result getAllPlace(){
        return placeService.getAllPlace();
    }

    @RequestMapping("/category-tree")
    @ResponseBody
    public Result getAllType(){
        return productTypeService.getAllType();
    }

    @RequestMapping("/unit-list")
    @ResponseBody
    public Result getAllUnit(){
        return unitService.getAllUnit();
    }

    //分页查询商品方法
    @RequestMapping("/product-page-list")
    @ResponseBody
    public Result ProductPage(Page page, ProductPageUtil productPageUtil){
        return productService.ProductPage(page,productPageUtil);
    }

    //上下架商品方法
    @RequestMapping("/state-change")
    @ResponseBody
    public Result productStateChange(@RequestBody Product product){
        return productService.productStateChange(product);
    }

    //上传图片方法，不需要三层架构
    @RequestMapping("/img-upload")
    @ResponseBody
    @CrossOrigin
    public Result upImg(MultipartFile file){
        try {
            File uploadFile = ResourceUtils.getFile(imgpath);
            String filename = file.getOriginalFilename();
            String path = uploadFile + "\\" + filename;
            file.transferTo(new File(path));
            System.out.println("验证"+path);
            return Result.ok("上传成功！");
        }catch (Exception e){
            return Result.err(Result.CODE_ERR_BUSINESS,"系统忙！");
        }
    }

    @RequestMapping("/product-add")
    @ResponseBody
    public Result addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    //批量删除货物
    @RequestMapping("/product-list-delete")
    @ResponseBody
    public Result deleteProducts(@RequestBody Integer[] ints){
        return productService.deleteProducts(ints);
    }

    @RequestMapping("/product-update")
    @ResponseBody
    public Result updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @RequestMapping("/product-delete/{id}")
    @ResponseBody
    public Result deleteOne(@PathVariable int id){
        return productService.deleteOne(id);
    }
}

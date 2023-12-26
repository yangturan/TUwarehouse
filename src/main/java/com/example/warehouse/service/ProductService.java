package com.example.warehouse.service;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.Product;
import com.example.warehouse.pojo.product.ProductPageUtil;
import com.example.warehouse.pojo.result.Result;

public interface ProductService {

    Result ProductPage(Page page, ProductPageUtil productPageUtil);

    Result productStateChange(Product product);

    Result addProduct(Product product);

    Result deleteProducts(Integer[] ints);

    Result updateProduct(Product product);

    Result deleteOne(int id);
}

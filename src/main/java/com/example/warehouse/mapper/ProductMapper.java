package com.example.warehouse.mapper;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.Product;
import com.example.warehouse.pojo.product.ProductPageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> productPage(@Param("page") Page page, @Param("util") ProductPageUtil productPageUtil);

    int productStateChange(@Param("pro") Product product);

    int addProduct(Product product);

    int deleteProducts(Integer[] ints);

    int updateProduct(Product product);

    int updateBrand(Product product);

    int updatePlace(Product product);

    int updateStore(Product product);

    int updateSupply(Product product);

    int updateType(Product product);

    int getAllProductCount(@Param("util") ProductPageUtil productPageUtil);

    int deleteOne(int id);
}

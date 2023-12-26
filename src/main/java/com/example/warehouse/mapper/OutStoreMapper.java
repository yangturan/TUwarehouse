package com.example.warehouse.mapper;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.OutStore;
import com.example.warehouse.pojo.product.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 32966
* @description 针对表【out_store(出库单)】的数据库操作Mapper
* @createDate 2023-12-23 14:57:03
* @Entity com.example.warehouse.pojo.product.OutStore
*/
@Mapper
public interface OutStoreMapper{

    int makeout(Product product);


    int getOutCount(OutStore outStore);

    List<OutStore> outPage(@Param("out") OutStore outStore, @Param("page") Page page);

    int selectUserId(OutStore outStore);

    int updateProduct(OutStore outStore);

    int updateOutStoreState(OutStore outStore);

    int selectProductInvent(OutStore outStore);
}





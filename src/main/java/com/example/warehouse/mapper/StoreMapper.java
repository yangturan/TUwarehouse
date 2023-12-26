package com.example.warehouse.mapper;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 32966
* @description 针对表【store(仓库表)】的数据库操作Mapper
* @createDate 2023-12-26 10:50:01
* @Entity com.example.warehouse.pojo.product.Store
*/
@Mapper
public interface StoreMapper {

    List<Store> getAllStore();

    int getStoreCount(Store store);

    List<Store> getStorePage(@Param("store") Store store, @Param("page") Page page);

    List<Store> validation(String storeNum);

    int makeStore(Store store);

    int deleteStore(int id);

    int updateStore(Store store);
}





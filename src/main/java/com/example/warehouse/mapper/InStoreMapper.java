package com.example.warehouse.mapper;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.InStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 32966
* @description 针对表【in_store(入库单)】的数据库操作Mapper
* @createDate 2023-12-24 15:45:52
* @Entity com.example.warehouse.pojo.product.InStore
*/
@Mapper
public interface InStoreMapper{

    int getAllInStore(InStore inStore);

    List<InStore> getPageInStore(@Param("inStore") InStore inStore,@Param("page") Page page);

    int updatebuyList(InStore instore);

    int makeInStore(InStore instore);

    int updateProduct(InStore inStore);

    int sureInStore(InStore inStore);

    int selectUserId(String userCode);
}





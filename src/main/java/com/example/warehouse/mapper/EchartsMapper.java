package com.example.warehouse.mapper;

import com.example.warehouse.pojo.ecahrts.StoreCountVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EchartsMapper {

    @Select("select s.store_id,s.store_name,ifnull(sum(p.product_invent),0) as totalInvent from product p,store s where p.store_id=s.store_id group by s.store_id")
    List<StoreCountVo> echarts();
}

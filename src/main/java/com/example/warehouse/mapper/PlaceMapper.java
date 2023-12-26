package com.example.warehouse.mapper;

import com.example.warehouse.pojo.product.Place;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 32966
* @description 针对表【place(产地)】的数据库操作Mapper
* @createDate 2023-12-17 15:14:32
* @Entity com.example.warehouse.pojo.product.Place
*/
@Mapper
public interface PlaceMapper{

    List<Place> getAllPlace();
}





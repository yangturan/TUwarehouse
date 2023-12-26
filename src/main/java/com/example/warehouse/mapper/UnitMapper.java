package com.example.warehouse.mapper;

import com.example.warehouse.pojo.product.Unit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 32966
* @description 针对表【unit(规格单位表)】的数据库操作Mapper
* @createDate 2023-12-17 15:22:33
* @Entity com.example.warehouse.pojo.product.Unit
*/
@Mapper
public interface UnitMapper {

    List<Unit> getAllUnit();
}





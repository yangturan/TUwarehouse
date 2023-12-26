package com.example.warehouse.service.impl;

import com.example.warehouse.pojo.product.Place;
import com.example.warehouse.service.PlaceService;
import com.example.warehouse.mapper.PlaceMapper;
import com.example.warehouse.pojo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 32966
* @description 针对表【place(产地)】的数据库操作Service实现
* @createDate 2023-12-17 15:14:32
*/
@Service
@Transactional
public class PlaceServiceImpl implements PlaceService{

    @Autowired
    private PlaceMapper placeMapper;
    @Override
    public Result getAllPlace() {
        List<Place> places=placeMapper.getAllPlace();
        if(places.size()>0){
            return Result.ok(places);
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"你没有任何地点");
        }
    }
}





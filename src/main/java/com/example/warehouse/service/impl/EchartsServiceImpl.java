package com.example.warehouse.service.impl;

import com.example.warehouse.mapper.EchartsMapper;
import com.example.warehouse.pojo.ecahrts.StoreCountVo;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EchartsServiceImpl implements EchartsService {

    @Autowired
    private EchartsMapper echartsMapper;
    @Override
    public Result echarts() {
        List<StoreCountVo>  list=echartsMapper.echarts();
        if(list.size()>0){
            return Result.ok(list);
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"你没有任何仓库！");
        }
    }
}

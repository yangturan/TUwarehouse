package com.example.warehouse.service.impl;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.Store;
import com.example.warehouse.mapper.StoreMapper;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 32966
* @description 针对表【store(仓库表)】的数据库操作Service实现
* @createDate 2023-12-26 10:50:01
*/
@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public Result getAllStore() {
        List<Store> stores=storeMapper.getAllStore();
        if(stores.size()>0){
            return Result.ok(stores);
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"没有任何仓库");
        }
    }

    @Override
    public List<Store> getStorePage(Store store, Page page) {
        int count=storeMapper.getStoreCount(store);
        page.setTotalNum(count);
        page.pageCount();
        page.indexStart();
        return storeMapper.getStorePage(store,page);
    }

    @Override
    public Result validation(String storeNum) {
        List<Store> list=storeMapper.validation(storeNum);
       return Result.ok(list.size()==0);
    }

    @Override
    public Result makeStore(Store store) {
        int i=storeMapper.makeStore(store);
        if(i>0){
            return Result.ok("添加成功！");
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"插入失败！");
        }
    }

    @Override
    public Result deleteStore(int id) {
        int i=storeMapper.deleteStore(id);
        if(i>0){
            return Result.ok("删除成功！");
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"删除失败！");
        }
    }

    @Override
    public Result updateStore(Store store) {
        int i=storeMapper.updateStore(store);
        if(i>0){
            return Result.ok("修改成功！");
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"修改失败！");
        }
    }
}





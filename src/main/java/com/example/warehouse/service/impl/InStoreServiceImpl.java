package com.example.warehouse.service.impl;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.InStore;
import com.example.warehouse.service.InStoreService;
import com.example.warehouse.mapper.InStoreMapper;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
* @author 32966
* @description 针对表【in_store(入库单)】的数据库操作Service实现
* @createDate 2023-12-24 15:45:52
*/
@Service
@Transactional
public class InStoreServiceImpl implements InStoreService{

    @Autowired
    private InStoreMapper inStoreMapper;

    @Override
    public Result inStorePage(Page page, InStore inStore) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            if(inStore.getStartTime()!="") {
                inStore.setStime(simpleDateFormat.parse(inStore.getStartTime()));
            }
            if(inStore.getEndTime()!="") {
                inStore.setEtime(simpleDateFormat.parse(inStore.getEndTime()));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int count=inStoreMapper.getAllInStore(inStore);
        page.setTotalNum(count);
        page.pageCount();
        page.indexStart();
        List<InStore> list=inStoreMapper.getPageInStore(inStore,page);
        if(list.size()>0){
            page.setResultList(list);
            return Result.ok(page);
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"你没有该条件下的入库单");
        }
    }

    @Override
    public Result buyListToInStore(InStore instore,String token) {
        int i=inStoreMapper.updatebuyList(instore);
        if(i>0){
            instore.setCreateBy(new TokenUtils().getCurrentUser(token).getUserId());
            int j=inStoreMapper.makeInStore(instore);
            if(j>0){
                return Result.ok("生成入库单成功!");
            }else{
                return Result.err(Result.CODE_ERR_BUSINESS,"生成入库单失败！");
            }
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"修改采购单状态失败！");
        }
    }

    @Override
    public Result sureInstore(InStore inStore) {
        int i1=inStoreMapper.selectUserId(inStore.getUserCode());
        inStore.setCreateBy(i1);
        int i=inStoreMapper.updateProduct(inStore);
        if(i>0){
            int j=inStoreMapper.sureInStore(inStore);
            if(j>0){
                return Result.ok("入库成功！");
            }else{
                return Result.err(Result.CODE_ERR_BUSINESS,"入库时出现错误！");
            }
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"修改商品数量失败");
        }
    }
}





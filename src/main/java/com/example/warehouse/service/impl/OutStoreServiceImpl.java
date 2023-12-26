package com.example.warehouse.service.impl;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.OutStore;
import com.example.warehouse.pojo.product.Product;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.pojo.users.CurrentUser;
import com.example.warehouse.service.OutStoreService;
import com.example.warehouse.mapper.OutStoreMapper;
import com.example.warehouse.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 32966
* @description 针对表【out_store(出库单)】的数据库操作Service实现
* @createDate 2023-12-23 14:57:03
*/
@Service
@Transactional
public class OutStoreServiceImpl implements OutStoreService{

    @Autowired
    private OutStoreMapper outStoreMapper;

    @Override
    public int makeout(Product product,String header) {
        CurrentUser currentUser = new TokenUtils().getCurrentUser(header);
        product.setCreateBy(currentUser.getUserId());
        return outStoreMapper.makeout(product);
    }

    @Override
    public List<OutStore> outpage(OutStore outStore, Page page) {
        int outCount = outStoreMapper.getOutCount(outStore);
        page.setTotalNum(outCount);
        page.pageCount();
        page.indexStart();
        return outStoreMapper.outPage(outStore,page);
    }

    @Override
    public Result sureOut(OutStore outStore) {
        int num=outStoreMapper.selectProductInvent(outStore);
        if(outStore.getOutNum()>num){
            return Result.err(Result.CODE_ERR_BUSINESS,"你的库存不足");
        }
        int i=outStoreMapper.selectUserId(outStore);
        outStore.setCreateBy(i);
        int j=outStoreMapper.updateProduct(outStore);
        if(j>0){
            int m=outStoreMapper.updateOutStoreState(outStore);
            if(m>0){
                return Result.ok("出库成功！");
            }else{
                return Result.err(Result.CODE_ERR_BUSINESS,"出库失败！");
            }
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"商品数量修改失败！");
        }
    }
}





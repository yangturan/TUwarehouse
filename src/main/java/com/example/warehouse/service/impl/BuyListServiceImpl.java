package com.example.warehouse.service.impl;

import com.example.warehouse.pojo.Page.Page;
import com.example.warehouse.pojo.product.BuyList;
import com.example.warehouse.pojo.result.Result;
import com.example.warehouse.service.BuyListService;
import com.example.warehouse.mapper.BuyListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
* @author 32966
* @description 针对表【buy_list(采购单)】的数据库操作Service实现
* @createDate 2023-12-22 14:37:46
*/
@Service
@Transactional
public class BuyListServiceImpl implements BuyListService{

    @Autowired
    private BuyListMapper buyListMapper;
    @Override
    public int buy(BuyList buyList) {
        return buyListMapper.buy(buyList);
    }

    @Override
    public Page buypage(Page page, BuyList buyList) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (buyList.getStartTime()!=null&&buyList.getStartTime()!="") {
                buyList.setStime(sdf.parse(buyList.getStartTime()));
            }
            if (buyList.getEndTime()!=null&&buyList.getEndTime()!="") {
                buyList.setEtime(sdf.parse(buyList.getEndTime()));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int allRoleCount = buyListMapper.getAllBuyCount(buyList);
        page.setTotalNum(allRoleCount);
        page.pageCount();
        page.indexStart();
        List<BuyList> buys=buyListMapper.buypage(page,buyList);
        page.setResultList(buys);
        return page;
    }

    @Override
    public int updateBuy(BuyList buyList) {
        return buyListMapper.updateBuy(buyList);
    }

}





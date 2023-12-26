package com.example.warehouse.pojo.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 *
 http://localhost:3000/api/user/user-list?userCode=
 &userType=&userState=&pageSize=5&pageNum=1&totalNum=0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page implements Serializable {
    private int pageSize; //每页多少行
    private int pageNum; //现在是第几页
    private int totalNum;  //总共多少数据
    private int pageCount; //总共多少页
    private int indexStart;  //当前页limit查询时的起始数值

    private List<?> resultList;  //返回的list列表，可以是role,auth,user

    public void pageCount(){
        this.pageCount=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
    }

    public void indexStart(){
        this.indexStart=pageSize*(pageNum-1);
    }
}

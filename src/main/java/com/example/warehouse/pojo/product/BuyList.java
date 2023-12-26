package com.example.warehouse.pojo.product;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 采购单
 * @TableName buy_list
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyList implements Serializable {
    /**
     *
     * **/
    private Integer buyId;

    /**
     *
     */
    private Integer productId;

    /**
     *
     */
    private Integer storeId;

    /**
     *
     */
    private Integer buyNum;

    /**
     *
     */
    private Integer factBuyNum;

    /**
     *
     */
    private Date buyTime;

    /**
     *
     */
    private Integer supplyId;

    /**
     *
     */
    private Integer placeId;

    /**
     *
     */
    private String buyUser;

    /**
     *
     */
    private String phone;

    /**
     * 0 否 1 是
     */
    private String isIn;

    //由于前端传来的是‘’不是null，为空时会报错，我们需要转换一下
    private String startTime;
    private Date stime;

    private String endTime;
    private Date etime;

    private String productName;
    private String storeName;
}

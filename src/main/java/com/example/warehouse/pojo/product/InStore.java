package com.example.warehouse.pojo.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 入库单
 * @TableName in_store
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InStore implements Serializable {
    /**
     *
     */
    private Integer insId;

    /**
     *
     */
    private Integer storeId;

    /**
     *
     */
    private Integer productId;

    /**
     *
     */
    private Integer inNum;

    /**
     *
     */
    private Integer createBy;

    /**
     *
     */
    private Date createTime;

    /**
     * 0 否 1 是
     */
    private String isIn;
    private String productName;
    private String endTime;
    private Date etime;
    private String startTime;
    private Date stime;
    private String storeName;
    private BigDecimal inPrice;
    private String userCode;
    private Integer factBuyNum;
    private Integer buyId;
}

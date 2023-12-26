package com.example.warehouse.pojo.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 出库单
 * @TableName out_store
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutStore implements Serializable {
    /**
     *
     */
    private Integer outsId;

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
    private Integer tallyId;

    /**
     *
     */
    private BigDecimal outPrice;

    /**
     *
     */
    private Integer outNum;

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
    private String isOut;
    private String productName;
    private String startTime;
    private Date stime;
    private String endTime;
    private Date etime;
    private String storeName;
    private String userCode;
}

package com.example.warehouse.pojo.product;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 仓库表
 * @TableName store
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Store implements Serializable {
    /**
     *
     */
    private Integer storeId;

    /**
     *
     */
    private String storeName;

    /**
     *
     */
    private String storeNum;

    /**
     *
     */
    private String storeAddress;

    /**
     *
     */
    private String concat;

    /**
     *
     */
    private String phone;

}

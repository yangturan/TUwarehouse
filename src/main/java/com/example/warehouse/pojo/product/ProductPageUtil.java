package com.example.warehouse.pojo.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * productName=&brandName=&typeName=&supplyName=&placeName=&upDownState=&isOverDate=
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPageUtil {
    private Integer storeId;
    private String productName;
    private String brandName;
    private String typeName;//unit单位类型
    private String supplyName;
    private String placeName;
    private String upDownState;
    private String isOverDate;//supp_date保质期
    private Timestamp now;//现在的时间
}

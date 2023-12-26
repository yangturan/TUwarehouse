package com.example.warehouse.pojo.product;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品分类表
 * @TableName product_type
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductType implements Serializable {
    /**
     *
     */
    private Integer typeId;

    /**
     *
     */
    private Integer parentId;

    /**
     *
     */
    private String typeCode;

    /**
     *
     */
    private String typeName;

    /**
     *
     */
    private String typeDesc;

    //用于装载二级类型
    private List<ProductType> childProductCategory;

}

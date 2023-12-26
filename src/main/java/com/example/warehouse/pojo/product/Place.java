package com.example.warehouse.pojo.product;

import java.io.Serializable;
import lombok.Data;

/**
 * 产地
 * @TableName place
 */
@Data
public class Place implements Serializable {
    /**
     *
     */
    private Integer placeId;

    /**
     *
     */
    private String placeName;

    /**
     *
     */
    private String placeNum;

    /**
     *
     */
    private String introduce;

    /**
     * 0:可用  1:不可用
     */
    private String isDelete;
}

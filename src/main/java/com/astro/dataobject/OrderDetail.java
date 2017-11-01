package com.astro.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by astro on 2017/10/28.
 */
@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;
    /* 商品数量*/
    private Integer productQuantity;
    /* 商品小图 */
    private String productIcon;


}

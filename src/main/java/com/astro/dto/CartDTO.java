package com.astro.dto;

import lombok.Data;

/**
 * Created by astro on 2017/11/5.
 */
@Data
public class CartDTO {

    private  String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}

package com.astro.enums;

import lombok.Getter;

/**
 * Created by astro on 2017/10/31.
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    ;

    private Integer code;

    private  String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

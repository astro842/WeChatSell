package com.astro.enums;

import lombok.Getter;

/**
 * Created by astro on 2017/10/31.
 */
@Getter
public enum ResultEnum {

    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),

    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_ISEMPTY(16,"订单详情为空"),
    ORDER_PAY_ERROR(17,"订单支付状态码不正确"),

    CART_EMPTY(18,"购物车为空"),
    ;

    private Integer code;

    private  String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

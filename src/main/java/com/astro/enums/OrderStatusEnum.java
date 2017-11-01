package com.astro.enums;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.Getter;

/**
 * Created by astro on 2017/10/28.
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已结束"),
        ;

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

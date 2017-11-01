package com.astro.dto;

import com.astro.dataobject.OrderDetail;
import com.astro.enums.OrderStatusEnum;
import com.astro.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by astro on 2017/10/31.
 */
@Data
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;
    /* 微信openid*/
    private String buyerOpenid;
    /* 订单金额*/
    private BigDecimal orderAmount;
    /*订单状态*/
    private Integer orderStatus;
    /*支付状态*/
    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    List<OrderDetail> orderDetailList;

}

package com.astro.dto;

import com.astro.Util.Date2LongSerializer;
import com.astro.Util.EnumUtil;
import com.astro.dataobject.OrderDetail;
import com.astro.dataobject.OrderMaster;
import com.astro.enums.OrderStatusEnum;
import com.astro.enums.PayStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();
    /*支付状态*/
    private Integer payStatus= PayStatusEnum.WAIT.getCode();

    List<OrderDetail> orderDetailList;
    /** 创建时间. */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    /** 更新时间. */

    private Date updateTime;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }



}

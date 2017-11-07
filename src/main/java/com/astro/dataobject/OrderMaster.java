package com.astro.dataobject;

import com.astro.enums.OrderStatusEnum;
import com.astro.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by astro on 2017/10/28.
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
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



//    @Transient
//    private List<OrderDetail> orderDetailList;

}


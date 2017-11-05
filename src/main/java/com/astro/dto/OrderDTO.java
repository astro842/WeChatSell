package com.astro.dto;

import com.astro.dataobject.OrderDetail;
import com.astro.dataobject.OrderMaster;
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
public class OrderDTO extends OrderMaster{



    List<OrderDetail> orderDetailList;

}

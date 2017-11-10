package com.astro.service;

import com.astro.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by astro on 2017/10/31.
 */
public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);

    OrderDTO findOne(String orderId);

    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    Page<OrderDTO> findList(Pageable pageable);


    /* 取消  完结  支付订单*/
    OrderDTO cancel(OrderDTO orderDTO);

    OrderDTO finish(OrderDTO orderDTO);

    OrderDTO paid(OrderDTO orderDTO);



}

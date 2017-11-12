package com.astro.service.impl;

import com.astro.dto.OrderDTO;
import com.astro.enums.ResultEnum;
import com.astro.exception.SellException;
import com.astro.service.BuyerService;
import com.astro.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by astro on 2017/11/12.
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService{

    @Autowired
    private OrderService orderService;


    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
         return checkOrderOwner(openid,orderId);

    }

    @Override
    public OrderDTO CancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderId == null){
            log.error("【取消订单】查不到订单，orderId={}",orderId );
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    public OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO=orderService.findOne(orderId);
        if (orderDTO == null){
            return  null;
        }
        if (!orderDTO.getOrderId().equals(openid)){
            log.error("【查询订单】订单的openId不一致，openid={},orderDTO={}",openid,orderDTO);
            throw  new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

}

package com.astro.service;

import com.astro.dto.OrderDTO;

/**
 * Created by astro on 2017/11/12.
 */
public interface BuyerService {

    OrderDTO findOrderOne(String openid,String orderId);


    OrderDTO CancelOrder(String openid,String orderId);


}

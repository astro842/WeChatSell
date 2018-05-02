package com.astro.service;

import com.astro.dataobject.BuyerCar;
import com.astro.dataobject.BuyerInfo;
import com.astro.dto.OrderDTO;

import java.util.List;

/**
 * Created by astro on 2017/11/12.
 */
public interface BuyerService {

    OrderDTO findOrderOne(String openid,String orderId);


    OrderDTO CancelOrder(String openid,String orderId);

    BuyerInfo save(BuyerInfo buyerInfo);

    BuyerInfo findByOpenId(String openid);

    //加入购物车
    int addBuyerCar(BuyerCar buyerCar);

    List<BuyerCar> getAddCar(String opendId);



}

package com.astro.service.impl;

import com.astro.dataobject.BuyerCar;
import com.astro.dataobject.BuyerInfo;
import com.astro.dto.OrderDTO;
import com.astro.enums.ResultEnum;
import com.astro.exception.SellException;
import com.astro.repository.BuyerCarDao;
import com.astro.repository.BuyerInfoRepository;
import com.astro.service.BuyerService;
import com.astro.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by astro on 2017/11/12.
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService{

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerInfoRepository repository;

    @Autowired
    private BuyerCarDao buyerCarDao;


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
        if (!orderDTO.getBuyerOpenid().equals(openid)){
            log.error("【查询订单】订单的openId不一致，openid={},orderDTO={}",openid,orderDTO);
            throw  new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

    @Override
    public BuyerInfo save(BuyerInfo buyerInfo) {
        return repository.save(buyerInfo);
    }

    @Override
    public BuyerInfo findByOpenId(String openid) {
        return repository.findByOpenid(openid);
    }


    @Override
    public int addBuyerCar(BuyerCar buyerCar) {

        return buyerCarDao.addBuyerCar(buyerCar);
    }

    @Override
    public List<BuyerCar> getAddCar(String opendId) {
        return  buyerCarDao.getBuyerCarByOpenid(opendId);
    }

}

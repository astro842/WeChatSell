package com.astro.service.impl;

import com.astro.dataobject.OrderDetail;
import com.astro.dataobject.ProductInfo;
import com.astro.dto.OrderDTO;
import com.astro.enums.ResultEnum;
import com.astro.exception.SellException;
import com.astro.service.OrderService;
import com.astro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by astro on 2017/10/31.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);

     @Override
    public OrderDTO create(OrderDTO orderDTO) {
        //查询商品
         for (OrderDetail orderDetail:orderDTO.getOrderDetailList()){
             ProductInfo productInfo=productService.findOne(orderDetail.getProductId());
            if (productInfo==null){
                    throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //订单计算总价    先算一件商品的总价，再算全部商品的总价
             orderAmount = orderDetail.getProductPrice().
                     multiply(new BigDecimal(orderDetail.
                             getProductQuantity()).add(orderAmount));

         }
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}

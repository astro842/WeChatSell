package com.astro.service.impl;

import com.astro.Util.KeyUtil;
import com.astro.converter.OrderMasterToOrderDtOConverter;
import com.astro.dataobject.OrderDetail;
import com.astro.dataobject.OrderMaster;
import com.astro.dataobject.ProductInfo;
import com.astro.dto.CartDTO;
import com.astro.dto.OrderDTO;
import com.astro.enums.OrderStatusEnum;
import com.astro.enums.PayStatusEnum;
import com.astro.enums.ResultEnum;
import com.astro.exception.SellException;
import com.astro.repository.OrderDetailRepository;
import com.astro.repository.OrderMasterRepository;
import com.astro.service.OrderService;
import com.astro.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by astro on 2017/10/31.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    String OrderId=KeyUtil.genUniqueKey();
    BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);

    List<CartDTO> cartDTOList=new ArrayList<>();

     @Override
     @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        //1.查询商品
         for (OrderDetail orderDetail:orderDTO.getOrderDetailList()){
             ProductInfo productInfo=productService.findOne(orderDetail.getProductId());
            if (productInfo==null){
                    throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.订单计算总价    先算一种商品的总价，再算全部商品的总价
             orderAmount = productInfo.getProductPrice().
                     multiply(new BigDecimal(orderDetail.
                             getProductQuantity())).add(orderAmount);

             //3.写入订单表
             //订单详情
             BeanUtils.copyProperties(productInfo,orderDetail);
             orderDetail.setDetailId(KeyUtil.genUniqueKey());
             orderDetail.setOrderId(OrderId);
             orderDetailRepository.save(orderDetail);


             CartDTO cartDTO=new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
             cartDTOList.add(cartDTO);
         }
         //4.写入订单主表
         OrderMaster orderMaster=new OrderMaster();
         orderDTO.setOrderId(OrderId);
         BeanUtils.copyProperties(orderDTO,orderMaster);
         orderMaster.setOrderAmount(orderAmount);
         orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
         orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
         orderMasterRepository.save(orderMaster);


         //5.扣库存
         productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

         OrderMaster orderMaster=orderMasterRepository.findOne(orderId);
         if (orderMaster == null){
             throw new SellException(ResultEnum.ORDER_NOT_EXIST);
         }
         List<OrderDetail> orderDetailList=orderDetailRepository.findByOrderId(orderId);
         if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
         }

         OrderDTO orderDTO=new OrderDTO();
         BeanUtils.copyProperties(orderMaster,orderDTO);
         orderDTO.setOrderDetailList(orderDetailList);

         return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList=OrderMasterToOrderDtOConverter.converter(orderMasterPage.getContent());

        Page<OrderDTO> orderDTOpage= new PageImpl(orderDTOList,pageable,orderMasterPage.getTotalElements());

        return orderDTOpage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
         OrderMaster orderMaster=new OrderMaster();

        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】订单状态码不正确，orderID={}，orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);

        OrderMaster update=orderMasterRepository.save(orderMaster);
        if (update == null) {
            log.error("【取消订单】状态码更新失败，orderMaster = { }",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        List<CartDTO> cartDTOList1= orderDTO.getOrderDetailList().stream().map(
                e -> new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.increaseStock(cartDTOList1);

        //如果已经支付，则退款
        if (orderDTO.getOrderStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            // TODO
        }

            return orderDTO;
        }


    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
         //判断订单状态
         if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
             log.error("【取消订单】订单状态码不正确，orderID={}，orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
             throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
         }

        //修改订单状态
         orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
         OrderMaster orderMaster=new OrderMaster();
         BeanUtils.copyProperties(orderDTO,orderMaster);
         OrderMaster update = orderMasterRepository.save(orderMaster);
         if (update ==null){
             log.error("【完结订单】状态码更新失败，orderMaster = { }",orderMaster);
             throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
         }


        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {

         //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【订单支付】订单状态码不正确，orderID={}，orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断订单支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付】订单支付状态码不正确，orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_ERROR);
        }

        //修改订单支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster update = orderMasterRepository.save(orderMaster);
        if (update == null){
            log.error("【订单支付】更新失败，orderMaster={}",orderMaster);
        }

        return orderDTO;
    }
}

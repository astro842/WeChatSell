package com.astro.service.impl;

import com.astro.dataobject.OrderDetail;
import com.astro.dto.OrderDTO;
import com.astro.enums.OrderStatusEnum;
import com.astro.enums.PayStatusEnum;
import com.astro.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by astro on 2017/11/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String OPENID="10929090";
    //private  final String orderid="1509876163808659307";
    private  final String orderid="1510039291933676796";

    @Test
    public void create() throws Exception {

        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("小黄");
        orderDTO.setBuyerAddress("广州南");
        orderDTO.setBuyerPhone("123456");
        orderDTO.setBuyerOpenid(OPENID);

        List<OrderDetail> orderDetailList=new ArrayList<>();
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setProductId("1");
        orderDetail.setProductQuantity(1);

        OrderDetail orderDetail2=new OrderDetail();
        orderDetail2.setProductId("2");
        orderDetail2.setProductQuantity(2);

        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);


        log.info("创建订单 result={}",result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = orderService.findOne(orderid);

        log.info("查询单个订单：orderDTO{}",orderDTO);
        Assert.assertEquals(orderid,orderDTO.getOrderId());

    }

    @Test
    public void findList() throws Exception {

        Pageable request=new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(OPENID, request);
        assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void allList() throws Exception {

        Pageable request=new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO=orderService.findOne(orderid);
        OrderDTO cancel = orderService.cancel(orderDTO);

        Assert.assertEquals( OrderStatusEnum.CANCEL.getCode(),cancel.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO=orderService.findOne(orderid);
        OrderDTO update = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),update.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO=orderService.findOne(orderid);
        OrderDTO update=orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),update.getPayStatus());

    }

    @Test
    public  void tt() throws  Exception{

    }
}
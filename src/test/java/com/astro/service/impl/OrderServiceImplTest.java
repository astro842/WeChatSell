package com.astro.service.impl;

import com.astro.dataobject.OrderDetail;
import com.astro.dto.OrderDTO;
import com.astro.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    }

    @Test
    public void findList() throws Exception {
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void paid() throws Exception {
    }

}
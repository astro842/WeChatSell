package com.astro.repository;

import com.astro.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by astro on 2017/10/29.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("23433");
        orderDetail.setOrderId("345");
        orderDetail.setProductIcon("adfadfaqwe");
        orderDetail.setProductId("3453er4");
        orderDetail.setProductName("你好yt");
        orderDetail.setProductPrice(new BigDecimal(1.2));
        orderDetail.setProductQuantity(2);

        OrderDetail save = repository.save(orderDetail);
        Assert.assertNotNull(save);


    }

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> byOrderId = repository.findByOrderId("345");
        Assert.assertNotEquals(0,byOrderId.size());

    }

}
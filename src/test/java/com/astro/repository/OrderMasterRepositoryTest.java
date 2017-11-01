package com.astro.repository;

import com.astro.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by astro on 2017/10/29.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;


    @Test
    public void saveTest(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("200");
        orderMaster.setBuyerName("小文");
        orderMaster.setBuyerPhone("110333");
        orderMaster.setBuyerAddress("北京");
        orderMaster.setBuyerOpenid("123");
        orderMaster.setOrderAmount(new BigDecimal(3.2));

        OrderMaster save = repository.save(orderMaster);
        Assert.assertNotNull(save);

    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request=new PageRequest(0,2);
        Page<OrderMaster> page = repository.findByBuyerOpenid("110110", request);
        System.out.println(page.getTotalElements());
    }

}
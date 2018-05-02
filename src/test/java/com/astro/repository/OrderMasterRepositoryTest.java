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
import java.util.Date;
import java.util.List;

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
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("20012");
        orderMaster.setBuyerName("小文12");
        orderMaster.setBuyerPhone("11033312");
        orderMaster.setBuyerAddress("北京12");
        orderMaster.setBuyerOpenid("12312");
        orderMaster.setOrderAmount(new BigDecimal(31.2));
//        orderMaster.setCreateTime(new Date());
//        orderMaster.setUpdateTime(new Date());

        OrderMaster save = repository.save(orderMaster);
        Assert.assertNotNull(save);

    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderMaster> page = repository.findByBuyerOpenid("110110", request);
        System.out.println(page.getTotalElements());
    }

    @Test
    public void find() throws Exception {
        OrderMaster master = repository.findByOrderId("1524558649613875314");
        System.out.println(master);
    }

    @Test
    public void getbyopenid() throws Exception {
        List<OrderMaster> list = repository.findByBuyerOpenid("oXFU40-wU6UVQCjd511kEMPiyAR4");
        System.out.println(list.size());
        System.out.println(list);
    }
}
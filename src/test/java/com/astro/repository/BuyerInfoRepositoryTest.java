package com.astro.repository;

import com.astro.dataobject.BuyerInfo;
import com.astro.service.BuyerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by astro on 2018/4/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerInfoRepositoryTest {

    @Autowired
    private BuyerInfoRepository repository;

    @Autowired
    private BuyerService buyerService;

    @Test
    public void findByOpenid1() throws Exception {
        BuyerInfo info = buyerService.findByOpenId("oXFU40-wU6UVQCjd511kEMPiyAR4");
        System.out.println(info);
    }



    @Test
    public void saveTest()throws Exception{
        BuyerInfo b=new BuyerInfo();
        b.setNickName("astro");
        b.setCity("gz");
        b.setHeadImgUrl("123");
        b.setOpenid("22222222");
        b.setCreateTime(new Date());
        repository.save(b);
    }


    @Test
    public void findByOpenid() throws Exception {
        BuyerInfo info = repository.findByOpenid("oXFU40-wU6UVQCjd511kEMPiyAR4");
        System.out.println(info);
    }


}
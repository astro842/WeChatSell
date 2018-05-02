package com.astro.repository;

import com.astro.dataobject.BuyerCar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by astro on 2018/4/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerCarDaoTest {

    @Autowired
    private BuyerCarDao buyerCarDao;

    @Test
    public void getBuyerCarByOpenid() throws Exception {

        List<BuyerCar> list = buyerCarDao.getBuyerCarByOpenid("111");
        System.out.println(list.toString());
        System.out.println(list.size());

    }

    @Test
    public void add() throws Exception {
        BuyerCar buyerCar=new BuyerCar();
        buyerCar.setOpenid("111");
        buyerCar.setProductId("1");
        buyerCar.setState(1);
        buyerCar.setProductNum("2");
        int i = buyerCarDao.addBuyerCar(buyerCar);
        System.out.println(i);
    }

    @Test
    public void update() throws Exception {
        int i = buyerCarDao.updateCarState("111");
        System.out.println("i="+i);
    }



}
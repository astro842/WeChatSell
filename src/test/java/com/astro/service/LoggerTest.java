package com.astro.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by astro on 2017/10/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    //private final Logger logger= LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1(){
        String name="123";
        log.debug("debug...");
        log.info("name:{}",name);
        log.error("error...");
    }

    @Test
    public void test2(){
        String name=null;
        System.out.println(name);
    }
}

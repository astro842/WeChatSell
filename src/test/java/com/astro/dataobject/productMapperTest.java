package com.astro.dataobject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by astro on 2018/4/4.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class productMapperTest {

    @Autowired
    private ProductMapper p;

    @Test
    public void selectByCategory() throws Exception {
        String name="零食";
        List<ProductInfo> list = p.selectByCategory(name);
        System.out.println(list.size());
        for (ProductInfo pi:list){
            System.out.println(pi);
        }

    }

}
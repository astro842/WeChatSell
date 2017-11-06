package com.astro.repository;

import com.astro.dataobject.ProductInfo;
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
 * Created by astro on 2017/10/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
   public void saveTest(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("123");
        productInfo.setProductName("苹果");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("好吃");
        productInfo.setProductIcon("http:///12312");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);

        ProductInfo save = repository.save(productInfo);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> list =repository.findByProductStatus(0);
        Assert.assertNotEquals(0,list.size());
        for(ProductInfo info:list){
            System.out.println(info);
        }


    }

}
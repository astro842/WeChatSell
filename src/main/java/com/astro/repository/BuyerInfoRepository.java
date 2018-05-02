package com.astro.repository;

import com.astro.dataobject.BuyerInfo;
import com.astro.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by astro on 2017/10/24.
 */
public interface BuyerInfoRepository extends JpaRepository<BuyerInfo,String>{

    //查找buyer
    BuyerInfo findByOpenid(String openId);


}

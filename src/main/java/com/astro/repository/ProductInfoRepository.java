package com.astro.repository;

import com.astro.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by astro on 2017/10/24.
 */
public interface ProductInfoRepository  extends JpaRepository<ProductInfo,String>{

    //查找上架上商品
    List<ProductInfo> findByProductStatus(Integer productStatus);
}

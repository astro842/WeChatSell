package com.astro.service;

import com.astro.dataobject.ProductCategory;

import java.util.List;

/**
 * Created by astro on 2017/10/23.
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}

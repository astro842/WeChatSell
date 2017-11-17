package com.astro.controller;


import com.astro.Util.KeyUtil;
import com.astro.dataobject.ProductCategory;
import com.astro.exception.SellException;
import com.astro.form.CategoryForm;
import com.astro.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(){
        Map<String,Object> map=new HashMap<>();
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);

        return new ModelAndView("category/list",map);
    }
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                              Map<String,Object> map){
        if (categoryId != null){
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("category",productCategory);
        }

        return new ModelAndView("category/index");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        ProductCategory category = new ProductCategory();
        try {
            if (form.getCategoryId() != null){
                category = categoryService.findOne(form.getCategoryId());
            }

            BeanUtils.copyProperties(form,category);
            category.setCreateTime(new Date());
            category.setUpdateTime(new Date());
            categoryService.save(category);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success",map);


    }

    }


package com.astro.controller;


import ch.qos.logback.classic.Logger;
import com.astro.Util.KeyUtil;
import com.astro.dataobject.ProductCategory;
import com.astro.dataobject.ProductInfo;
import com.astro.dto.OrderDTO;
import com.astro.exception.SellException;
import com.astro.form.ProductForm;
import com.astro.service.CategoryService;
import com.astro.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("seller/product")
@Slf4j
public class SellerProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page" ,defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "3") Integer size,
                             Map<String,Object> map){

        Pageable pageable=new PageRequest(page-1,size);
        Page<ProductInfo> productInfos = productService.findAll(pageable);
        map.put("productInfos",productInfos);
        map.put("currentPage",page);

        ModelAndView model=new ModelAndView("product/list",map);
        return model;

    }

    @GetMapping("/onsale")
    public ModelAndView onSale(@RequestParam("productid") String productId,
                               Map<String,Object> map){
        try {
            productService.onSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error");
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);

    }

    @GetMapping("/offsale")
    public ModelAndView offSale(@RequestParam("productid") String productId,
                               Map<String,Object> map){
        try {
            productService.offSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error");
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                              Map<String,Object> map){

        if(! StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productinfo",productInfo);
        }

        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);

        return new ModelAndView("product/index",map);

    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
      if (bindingResult.hasErrors()){
          map.put("msg", bindingResult.getFieldError().getDefaultMessage());
          map.put("url", "/sell/seller/product/index");
          log.error("出错....{}",bindingResult.getFieldError().getDefaultMessage());
          return new ModelAndView("common/error", map);
      }
        ProductInfo productInfo=new ProductInfo();

      try {
          if (!StringUtils.isEmpty((form.getProductId()).trim())){
              productInfo = productService.findOne(form.getProductId());
          }else {
              form.setProductId(KeyUtil.genUniqueKey());
          }

          BeanUtils.copyProperties(form,productInfo);
          productInfo.setCreateTime(new Date());
          productInfo.setUpdateTime(new Date());
          productService.save(productInfo);
      }catch (SellException e){
          map.put("msg",e.getMessage());
          map.put("url","/sell/seller/product/index");
          log.error("出错....{}",e.getMessage());
          return new ModelAndView("common/error",map);
      }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }


}

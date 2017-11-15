package com.astro.controller;


import com.astro.dataobject.ProductInfo;
import com.astro.dto.OrderDTO;
import com.astro.exception.SellException;
import com.astro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("seller/product")
public class SellerProductController {


    @Autowired
    private ProductService productService;

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

}

package com.astro.controller;


import com.astro.dataobject.ProductInfo;
import com.astro.dto.OrderDTO;
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

        ModelAndView model=new ModelAndView("seller/list",map);
        return model;

    }

}

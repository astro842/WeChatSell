package com.astro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by astro on 2018/4/1.
 */
@Controller
@RequestMapping("/index")
public class TotalController {
    @RequestMapping("/index")
    public String index(){
        return "buyer/index";
    }
    @RequestMapping("/productDetail")
    public String detail(){
        return "buyer/productDetail";
    }



}

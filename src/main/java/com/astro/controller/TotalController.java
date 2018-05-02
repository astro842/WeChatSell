package com.astro.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * Created by astro on 2018/4/1.
 */
@Controller
@RequestMapping("/index")
@Slf4j
public class TotalController {
    @RequestMapping("/index")
    public String index(HttpServletResponse response,
                        @RequestParam(value = "openId", required = false) String openId) {
        log.info("------indexPage---openid:" + openId);
        addCookie(response, openId);
        return "buyer/index";
    }

    @RequestMapping("/productDetail")
    public String detail() {
        return "buyer/productDetail";
    }

    @RequestMapping("/orderDetail")
    public String orderDetail() {
        return "buyer/orderDetail";
    }


    @RequestMapping("/login")
    public String indexLogin() {
        //String url = "http://o2owechat.free.ngrok.cc/sell/wechat/authorize?returnUrl=http://o2owechat.free.ngrok.cc/sell/index/index";
         String testUrl="http://zdm47b.natappfree.cc";
         String url=testUrl+"/sell/wechat/authorize?returnUrl="+testUrl+"/sell/index/index";
        log.info("-----------访问index/login,重定向到：" + url);
        return "redirect:" + url;
    }

    private void addCookie(HttpServletResponse response, String openId) {
        log.info("---------把openId写入客户端--------");
        Cookie cookie = new Cookie("openId", openId);
        cookie.setMaxAge(1000);
        cookie.setPath("/");
        response.addCookie(cookie);
    }



}

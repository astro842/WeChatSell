package com.astro.controller;

import com.astro.Util.ResultVOUtil;
import com.astro.VO.ResultVO;
import com.astro.config.GetCookies;
import com.astro.dataobject.BuyerCar;
import com.astro.dataobject.BuyerInfo;
import com.astro.dataobject.OrderMaster;
import com.astro.dataobject.ProductInfo;
import com.astro.service.BuyerService;
import com.astro.service.OrderService;
import com.astro.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by astro on 2018/4/7.
 */
@RestController
@Slf4j
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/getUserInfo")
    public ResultVO<WxMpUser> getUserInfo(HttpServletRequest request) {
         String openid= GetCookies.getUserCookies(request);
         BuyerInfo buyer = buyerService.findByOpenId(openid);
        return ResultVOUtil.success(buyer);

    }

    @RequestMapping("/getAddCar")
    public ResultVO<WxMpUser> getCar(HttpServletRequest request) {
        String openid= GetCookies.getUserCookies(request);
        log.info("-----------openId-----------"+openid);
        List<BuyerCar> carList = buyerService.getAddCar(openid);
        log.info("-----------carList-----------"+carList);
        return ResultVOUtil.success(carList);
    }

    @RequestMapping("/getAddCarPrice")
    public ResultVO<Object> getCarPrice(HttpServletRequest request) {
        String openid= GetCookies.getUserCookies(request);
        log.info("-----------openId-----------"+openid);
        List<BuyerCar> carList = buyerService.getAddCar(openid);

        BigDecimal totalPrice=new BigDecimal(0);
        for (BuyerCar car:carList){
            //map.put(car.getProductId(),car.getProductNum());
            ProductInfo one = productService.findOne(car.getProductId());
            BigDecimal price = one.getProductPrice();
          //  totalPrice=price.multiply(Integer.parseInt(car.getProductNum()));
            totalPrice= totalPrice.add(price.multiply(new BigDecimal(car.getProductNum())));
        }

        return ResultVOUtil.success(totalPrice);
    }


    @RequestMapping("/addcar")
    public ResultVO addcar(HttpServletRequest request){
        String userCookies = GetCookies.getUserCookies(request);
        log.info("---------addcar的Cookies--------"+userCookies);
        BuyerCar car=new BuyerCar();
        car.setOpenid(userCookies);
        car.setProductId(request.getParameter("productId"));
        car.setProductNum(request.getParameter("productNum"));
        car.setState(0);
        buyerService.addBuyerCar(car);
        return ResultVOUtil.success(car);
    }

    @RequestMapping("/getbuyerOrder")
    public ResultVO getbuyerOrder(HttpServletRequest request){
        String userCookies = GetCookies.getUserCookies(request);
        log.info("---------getBuyerOrder的Cookies--------"+userCookies);
        List<OrderMaster> list=orderService.findOrderList(userCookies);
        return ResultVOUtil.success(list);
    }



}

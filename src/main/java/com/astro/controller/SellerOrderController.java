package com.astro.controller;


import com.astro.dataobject.OrderDetail;
import com.astro.dto.OrderDTO;
import com.astro.enums.ResultEnum;
import com.astro.exception.SellException;
import com.astro.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;




    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page" ,defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "3") Integer size,
                             Map<String,Object> map){

        Pageable pageable=new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageable);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);

        ModelAndView model=new ModelAndView("order/list",map);
        return model;

    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderid") String orderId,
                               Map<String,String> map){

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);

        }catch (SellException e){
            log.error("【卖家端取消订单】发生异常{}",e);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");

        return new ModelAndView("common/success",map);
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderid") String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO=new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);

        }catch (SellException e){
                log.error("【卖家端查询订单详情】 发生异常{}",e);
                map.put("msg",e.getMessage());
                map.put("url","/sell/seller/order/list");
             return new ModelAndView("common/error",map);
        }
        map.put("orderDTO",orderDTO);
        ModelAndView model=new ModelAndView("order/detail",map);

        return model;
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderid") String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO=new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
            OrderDTO finish = orderService.finish(orderDTO);

        }catch (SellException e){
            log.error("【卖家端完结订单】 发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");
        ModelAndView model=new ModelAndView("common/success",map);

        return model;
    }

}

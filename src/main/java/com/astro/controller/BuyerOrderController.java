package com.astro.controller;

import com.astro.Util.ResultVOUtil;
import com.astro.VO.ResultVO;
import com.astro.converter.OrderFormToOrderDTO;
import com.astro.dto.OrderDTO;
import com.astro.enums.ResultEnum;
import com.astro.exception.SellException;
import com.astro.form.OrderForm;
import com.astro.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.BindException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by astro on 2017/11/1.
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    //创建订单
    @RequestMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            log.error("【创建订单】订单参数不正确,OrderForm= {}", orderForm);
            throw  new SellException(ResultEnum.PARAM_ERROR.getCode(),
                                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO= OrderFormToOrderDTO.converter(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车为空");
            throw  new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result=orderService.create(orderDTO);

        Map<String,String> map=new HashMap<>();
        map.put("orderid",result.getOrderId());

        return ResultVOUtil.success(map);
    }

    //订单列表

    //订单详情

    //取消订单
}

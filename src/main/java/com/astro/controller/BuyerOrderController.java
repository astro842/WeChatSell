package com.astro.controller;

import com.astro.Util.ResultVOUtil;
import com.astro.VO.ResultVO;
import com.astro.converter.OrderFormToOrderDTO;
import com.astro.dataobject.OrderDetail;
import com.astro.dto.OrderDTO;
import com.astro.enums.ResultEnum;
import com.astro.exception.SellException;
import com.astro.form.OrderForm;
import com.astro.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.BindException;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping("/list")
    public ResultVO<List<OrderDTO>>  list(@RequestParam("openid") String openid,
                                          @RequestParam(value = "page",defaultValue = "0") Integer page,
                                          @RequestParam(value = "size",defaultValue = "10") Integer size){

        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        Pageable pageable=new PageRequest(page,size);
        Page<OrderDTO> list = orderService.findList(openid, pageable);
        System.out.println(list);

        return ResultVOUtil.success(list.getContent());
    }


    //订单详情
    @GetMapping("/detail")
    public ResultVO<List<OrderDetail>> detail(@RequestParam("openid") String openid,
                                              @RequestParam("orderid") String orderid){

        //TODO 不安全 为验证opendid
        OrderDTO orderDTO = orderService.findOne(orderid);
        return ResultVOUtil.success(orderDTO);
    }


    //取消订单
    @PostMapping("/cancle")
    public ResultVO cancle(@RequestParam("openid") String openid,
                                              @RequestParam("orderid") String orderid){

        //TODO 不安全 为验证opendid
        OrderDTO orderDTO = orderService.findOne(orderid);
        orderService.cancel(orderDTO);
        return ResultVOUtil.success();
    }
}

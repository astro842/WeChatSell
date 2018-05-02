package com.astro.converter;

import com.astro.dataobject.BuyerCar;
import com.astro.dataobject.OrderDetail;
import com.astro.dto.OrderDTO;
import com.astro.enums.ResultEnum;
import com.astro.exception.SellException;
import com.astro.form.MyForm;
import com.astro.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MyToOrderDTO {

    public static OrderDTO converter(MyForm myForm, String openId, List<BuyerCar> carList) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerOpenid(openId);
        orderDTO.setBuyerPhone(myForm.getPhone());
        orderDTO.setBuyerAddress(myForm.getAddress());
        orderDTO.setBuyerName(myForm.getName());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        for(BuyerCar car:carList){
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setProductId( car.getProductId());
            orderDetail.setProductQuantity(Integer.parseInt(car.getProductNum()));
            orderDetailList.add(orderDetail);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}

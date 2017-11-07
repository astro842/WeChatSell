package com.astro.converter;

import com.astro.dataobject.OrderMaster;
import com.astro.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class OrderMasterToOrderDtOConverter {

    public static OrderDTO converter(OrderMaster orderMaster){
            OrderDTO orderDTO=new OrderDTO();
            BeanUtils.copyProperties(orderMaster,orderDTO);
            return orderDTO;
    }

    public static List<OrderDTO>  converter(List<OrderMaster> orderMasterList){

        return orderMasterList.stream().map(  e ->
                    converter(e)
        ).collect(Collectors.toList());
    }
}

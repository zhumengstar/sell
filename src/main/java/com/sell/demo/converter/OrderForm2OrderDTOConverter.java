package com.sell.demo.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sell.demo.dataobject.OrderDetail;
import com.sell.demo.dto.OrderDTO;
import com.sell.demo.enums.ResultEnum;
import com.sell.demo.exception.SellException;
import com.sell.demo.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();




        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenId(orderForm.getOpenid());
//        BeanUtils 变量名不对应
        List<OrderDetail> orderDetailList = new ArrayList<>();

        try {
            orderDetailList=gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){
            }.getType());

        } catch (Exception e) {
            log.error("【对象转换】错误，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}

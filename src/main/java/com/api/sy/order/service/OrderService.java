package com.api.sy.order.service;

import com.api.sy.order.dto.OrderListDto;

public interface OrderService {
    OrderListDto.Response getOrderList(OrderListDto.Request reqDto);


}
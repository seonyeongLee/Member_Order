package com.api.idus.order.service;

import com.api.idus.order.dto.OrderListDto;

public interface OrderService {
    OrderListDto.Response getOrderList(OrderListDto.Request reqDto);


}
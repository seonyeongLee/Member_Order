package com.api.sy.order.controller;

import com.api.sy.common.dto.ErrorFieldDto;
import com.api.sy.order.dto.OrderListDto;
import com.api.sy.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/order")
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping(value = "/getMbrOrderList.json")
    public OrderListDto.Response getMbrOrderList(@RequestBody OrderListDto.Request reqDto,
                                                 BindingResult bindingResult) {
        OrderListDto.Response resDto = new OrderListDto.Response();

        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();

            List<ErrorFieldDto> list = new ArrayList<>();

            ErrorFieldDto errorDto;
            for (ObjectError error : errorList) {
                errorDto = ErrorFieldDto.builder()
                        .field(((FieldError)error).getField())
                        .reason(error.getDefaultMessage())
                        .build();
                list.add(errorDto);
            }
            OrderListDto.RespHeadData header = OrderListDto.RespHeadData.builder()
                    .rst("F").rstList(list)
                    .build();

            resDto.setHeadData(header);
            return resDto;
        }

        return orderService.getOrderList(reqDto);
    }



}
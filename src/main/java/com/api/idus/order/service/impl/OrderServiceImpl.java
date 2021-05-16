package com.api.idus.order.service.impl;

import com.api.idus.common.utility.MapperUtility;
import com.api.idus.order.dto.OrderListDto;
import com.api.idus.order.entity.MemberOrderTrn;
import com.api.idus.order.repository.MemberOrderTrnRepository;
import com.api.idus.order.repository.specification.MemberOrderTrnSpecification;
import com.api.idus.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MemberOrderTrnRepository moTrnRepository;

    @Override
    public OrderListDto.Response getOrderList(OrderListDto.Request reqDto) {
        OrderListDto.Response resDto = new OrderListDto.Response();

        Specification spec = Specification.where(MemberOrderTrnSpecification.equalMemberId(reqDto.getMemberId()));

        List<MemberOrderTrn> list = moTrnRepository.findAll(spec);

        List<OrderListDto.RespBodyData> body = MapperUtility.convertList(list, OrderListDto.RespBodyData.class);
        resDto.setBodyData(body);

        OrderListDto.RespHeadData header = OrderListDto.RespHeadData.builder()
                .rst("S").build();
        resDto.setHeadData(header);

        return resDto;
    }



}
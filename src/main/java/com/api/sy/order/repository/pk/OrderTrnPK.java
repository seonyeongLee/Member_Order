package com.api.sy.order.repository.pk;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderTrnPK implements Serializable {
    private String orderNum;

    private Long memberId;
}
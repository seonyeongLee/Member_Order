package com.api.idus.order.entity;

import com.api.idus.order.repository.pk.OrderTrnPK;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MEMBER_ORDER_TRN", schema = "db")
@IdClass(OrderTrnPK.class)
@Getter
@Setter
public class MemberOrderTrn implements Serializable {
    @Id
    @Column(name = "MEMBER_ID", length = 6, nullable = false)
    private Long memberId;

    @Id
    @Column(name = "ORDER_NUM", length = 12, nullable = false)
    private String orderNum;

}
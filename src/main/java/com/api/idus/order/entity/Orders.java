package com.api.idus.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS", schema = "db")
@SequenceGenerator(
        name = "ORDER_SEQ_GENERATOR",
        sequenceName = "PON",   //Product Order Num
        initialValue = 1, allocationSize = 1)
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "ORDER_SEQ_GENERATOR")
    @Column(name = "ORDER_NUM", length = 12, nullable = false)
    private String orderNum;

    @Column(name = "PRODUCT_NM", length = 100, nullable = false)
    private String productNm;

    @Column(name = "PAYMENT_DT", nullable = false)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    @CreationTimestamp
    private LocalDateTime paymentDt;

}
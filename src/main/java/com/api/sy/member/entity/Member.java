package com.api.sy.member.entity;

import com.api.sy.order.entity.Orders;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEMBER", schema = "db")
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 100000,
        allocationSize = 1
)
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "MEMBER_ID", length = 6, nullable = false)
    private Long memberId;

    @Column(name = "NAME", length = 20, nullable = false)
    private String name;

    @Column(name = "NICKNAME", length = 30, nullable = false)
    private String nickname;

    @Column(name = "PASSWORD", length = 100, nullable = false)
    private String password;

    @Column(name = "PHONE", length = 20, nullable = false)
    private String phone;

    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @Column(name = "GENDER", length = 1)
    private String gender;

    @OneToMany
    @OrderBy("paymentDt desc")
    @JoinTable(name = "MEMBER_ORDER_TRN",
                joinColumns = @JoinColumn(name = "MEMBER_ID"),
                inverseJoinColumns = @JoinColumn(name = "ORDER_NUM")
    )
    private List<Orders> orders = new ArrayList<Orders>();


}
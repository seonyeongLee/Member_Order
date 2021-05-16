package com.api.idus.order.repository.specification;

import com.api.idus.order.entity.MemberOrderTrn;
import com.api.idus.order.entity.Orders;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MemberOrderTrnSpecification {
    public static Specification<MemberOrderTrn> equalMemberId(Long memberId) {
        return new Specification<MemberOrderTrn>() {
            @Override
            public Predicate toPredicate(Root<MemberOrderTrn> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.equal(root.get("memberId"), memberId);
            }
        };
    }

}
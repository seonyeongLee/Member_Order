package com.api.idus.order.repository.specification;

import com.api.idus.order.entity.Orders;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OrderSpecification {
    public static Specification<Orders> equalMemberId(String memberId) {
        /*return new Specification<Orders>() {
            @Override
            public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.equal(root.get("memberId"), memberId);
            }
        };*/

        return (Specification<Orders>) ( (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("memberId"), memberId)
        );
    }

}
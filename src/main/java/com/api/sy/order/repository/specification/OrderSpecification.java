package com.api.sy.order.repository.specification;

import com.api.sy.order.entity.Orders;
import org.springframework.data.jpa.domain.Specification;

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
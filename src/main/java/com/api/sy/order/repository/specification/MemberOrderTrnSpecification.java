package com.api.sy.order.repository.specification;

import com.api.sy.order.entity.MemberOrderTrn;
import org.springframework.data.jpa.domain.Specification;

public class MemberOrderTrnSpecification {
    public static Specification<MemberOrderTrn> equalMemberId(Long memberId) {
        /*
         *  해당부분 람다식으로 변경 가능
        return new Specification<MemberOrderTrn>() {
            @Override
            public Predicate toPredicate(Root<MemberOrderTrn> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.equal(root.get("memberId"), memberId);
            }
        };
        */

        return (Specification<MemberOrderTrn>) ((root, query, builder) ->
                builder.equal(root.get("memberId"), memberId)
        );
    }

}
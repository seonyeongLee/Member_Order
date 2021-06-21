package com.api.sy.member.repository.specification;

import com.api.sy.member.entity.Member;
import org.springframework.data.jpa.domain.Specification;

public class MemberSpecification {

    public static Specification<Member> equalName(String name) {
        /*return new Specification<Member>() {
            @Override
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.equal(root.get("name"), name);
            }
        };*/

        return (Specification<Member>) ( (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name)
        );
    }

    public static Specification<Member> equalNickname(String nickname) {
        /*return new Specification<Member>() {
            @Override
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.equal(root.get("nickname"), nickname);
            }
        };*/

        return (Specification<Member>) ( (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("nickname"), nickname)
        );
    }

    public static Specification<Member> equalEmail(String email) {
        /*return new Specification<Member>() {
            @Override
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.equal(root.get("email"), email);
            }
        };*/

        return (Specification<Member>) ( (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("email"), email)
        );
    }

}

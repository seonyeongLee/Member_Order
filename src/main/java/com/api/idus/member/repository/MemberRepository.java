package com.api.idus.member.repository;

import com.api.idus.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member>,
                                                CrudRepository<Member, Long> {
    Page<Member> findAll(Specification spec, Pageable pageable);

    Optional<Member> findById(Long memberId);

}
package com.api.idus.order.repository;

import com.api.idus.order.entity.MemberOrderTrn;
import com.api.idus.order.entity.Orders;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MemberOrderTrnRepository extends JpaRepository<MemberOrderTrn, Long>, JpaSpecificationExecutor<MemberOrderTrn>,
                                            CrudRepository<MemberOrderTrn, Long> {
    List<MemberOrderTrn> findAll(Specification spec);


}
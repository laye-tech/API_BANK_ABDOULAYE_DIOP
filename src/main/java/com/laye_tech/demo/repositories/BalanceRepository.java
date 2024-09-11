package com.laye_tech.demo.repositories;

import com.laye_tech.demo.entities.BalanceEntity;
import com.laye_tech.demo.entities.QBalanceEntity;
import com.laye_tech.demo.entities.QCompteEntity;
import com.laye_tech.demo.entities.VirementEntity;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceEntity, Long> , QuerydslPredicateExecutor<BalanceEntity> {

    default Page<BalanceEntity> findAllByFilter(Pageable pageable,Double solde,Long compteId , String sortBy,Boolean ascending) {
        var booleanBuilder = new BooleanBuilder();
        Sort sort=Sort.unsorted();

        if(Objects.nonNull(solde)){
            booleanBuilder.and(QBalanceEntity.balanceEntity.solde.eq(solde));
        }
        if(Objects.nonNull(compteId)){
            booleanBuilder.and(QBalanceEntity.balanceEntity.compte.id.eq(compteId));
        }

        if(org.apache.commons.lang3.StringUtils.isNotEmpty(sortBy)) {
            sort = Sort.by(sortBy);
        }
        if((Objects.nonNull(ascending))) {
            sort.ascending();
        }

        var pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return findAll(booleanBuilder, pageable);

    }
}

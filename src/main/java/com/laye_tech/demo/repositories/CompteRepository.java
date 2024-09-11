package com.laye_tech.demo.repositories;

import org.apache.commons.lang3.StringUtils;

import com.laye_tech.demo.entities.CompteEntity;
import com.laye_tech.demo.entities.QCompteEntity;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public interface CompteRepository extends JpaRepository<CompteEntity, Long> , QuerydslPredicateExecutor<CompteEntity> {
    default Page<CompteEntity> findAllByFilter(Pageable pageable,String nom,String prenom , String sortBy,Boolean ascending) {
        var booleanBuilder = new BooleanBuilder();
        Sort sort=Sort.unsorted();

        if(StringUtils.isNotEmpty(nom)){
            booleanBuilder.and(QCompteEntity.compteEntity.nom.containsIgnoreCase(nom));
        }

        if(StringUtils.isNotEmpty(prenom)){
            booleanBuilder.and(QCompteEntity.compteEntity.prenom.containsIgnoreCase(prenom));
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

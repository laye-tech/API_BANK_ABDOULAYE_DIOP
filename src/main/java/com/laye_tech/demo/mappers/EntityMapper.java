package com.laye_tech.demo.mappers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EntityMapper<D, E> {

    E asEntity(D dto);

    D asDto(E entity);

    List<D> parse(List<E> entities);

    List<E> parseToEntity(List<D> entities);

    default Page<D> asPage(Page<E> entityPage, List<E> content) {
        Pageable pageable = entityPage.getPageable();
        List<D> dtoList = parse(content);
        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    default Page<D> asPage(Page<E> entityPage) {
        Pageable pageable = entityPage.getPageable();
        List<D> dtoList = parse(entityPage.getContent());
        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    default  Page<D> asPage(List<E> entities, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<D> dtoList = parse(entities);
        return new PageImpl<>(dtoList, pageable, entities.size());
    }
}

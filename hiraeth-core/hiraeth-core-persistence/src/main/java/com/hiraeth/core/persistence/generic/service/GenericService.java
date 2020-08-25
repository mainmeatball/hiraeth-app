package com.hiraeth.core.persistence.generic.service;

import com.hiraeth.core.persistence.generic.entity.AbstractEntity;
import com.hiraeth.core.utils.function.TriFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

public interface GenericService<E extends AbstractEntity> {
    E save(E entity);

    E findById(long id);

    List<E> find(CriteriaQuery<E> cq);

    List<E> find(Map<String, Object> params);

    List<E> findBy(String param, Object value);

    List<E> findByCondition(TriFunction<CriteriaBuilder, CriteriaQuery<E>, Root<E>, Predicate> func);

    List<E> findAll();

    void remove(long id);
}

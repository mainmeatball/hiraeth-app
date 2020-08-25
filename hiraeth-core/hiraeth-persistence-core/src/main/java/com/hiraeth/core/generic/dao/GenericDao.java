package com.hiraeth.core.generic.dao;

import com.hiraeth.core.generic.entity.AbstractEntity;
import com.hiraeth.core.generic.function.TriFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

public interface GenericDao<T extends AbstractEntity> {

    Class<T> getPersistentClass();

    T save(T entity);

    T findById(long id);

    List<T> find(CriteriaQuery<T> cq);

    List<T> find(Map<String, Object> params);

    List<T> findBy(String param, Object value);

    List<T> findByCondition(TriFunction<CriteriaBuilder, CriteriaQuery<T>, Root<T>, Predicate> func);

    List<T> findAll();

    void remove(long id);

    CriteriaQuery<T> constructQuery(TriFunction<CriteriaBuilder, CriteriaQuery<T>, Root<T>, Predicate> func);
}

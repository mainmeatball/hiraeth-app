package com.hiraeth.core.generic.service.impl;

import com.hiraeth.core.generic.dao.AbstractGenericDao;
import com.hiraeth.core.generic.entity.AbstractEntity;
import com.hiraeth.core.generic.function.TriFunction;
import com.hiraeth.core.generic.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

public abstract class GenericServiceImpl<E extends AbstractEntity, DAO extends AbstractGenericDao<E>>
        implements GenericService<E> {

    private DAO dao;

    public DAO getDao() {
        return dao;
    }

    @Override
    public E save(E entity) {
        return getDao().save(entity);
    }

    @Override
    public E findById(long id) {
        return getDao().findById(id);
    }

    @Override
    public List<E> find(CriteriaQuery<E> cq) {
        return getDao().find(cq);
    }

    @Override
    public List<E> find(Map<String, Object> params) {
        return getDao().find(params);
    }

    @Override
    public List<E> findBy(String param, Object value) {
        return getDao().findBy(param, value);
    }

    @Override
    public List<E> findByCondition(TriFunction<CriteriaBuilder, CriteriaQuery<E>, Root<E>, Predicate> func) {
        return getDao().findByCondition(func);
    }

    @Override
    public List<E> findAll() {
        return getDao().findAll();
    }

    @Override
    public void remove(long id) {
        getDao().remove(id);
    }

    @Autowired
    public void setDao(final DAO dao) {
        this.dao = dao;
    }
}

package com.hiraeth.core.persistence.generic.dao;

import com.hiraeth.core.persistence.generic.entity.AbstractEntity;
import com.hiraeth.core.utils.function.TriFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public abstract class AbstractGenericDao<T extends AbstractEntity> implements GenericDao<T> {

    private final Class<T> persistentClass;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unchecked")
    public AbstractGenericDao() {
        persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public abstract EntityManager getEntityManager();

    @Override
    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    @Transactional
    public T save(final T entity) {
        if (entity.isNew()) {
            getEntityManager().persist(entity);
            return entity;
        }
        return getEntityManager().merge(entity);
    }

    @Override
    @Transactional
    public T findById(final long id) {
        return getEntityManager().find(persistentClass, id);
    }

    @Override
    @Transactional
    public List<T> find(final CriteriaQuery<T> cq) {
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    @Transactional
    public List<T> find(final Map<String, Object> params) {
        final CriteriaQuery<T> criteriaQuery = constructQuery((cb, cq, root) -> {
            final Predicate[] predicates = params.entrySet()
                    .stream()
                    .map(e -> cb.equal(root.get(e.getKey()), e.getValue()))
                    .toArray(Predicate[]::new);
            return cb.and(predicates);
        });
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional
    public List<T> findBy(final String param, final Object value) {
        final CriteriaQuery<T> q = constructQuery((cb, cq, root) -> cb.equal(root.get(param), value));
        return getEntityManager().createQuery(q).getResultList();
    }

    @Override
    @Transactional
    public List<T> findAll() {
        return getEntityManager().createQuery(constructQuery((cb, cq, root) -> cb.and())).getResultList();
    }

    @Override
    @Transactional
    public List<T> findByCondition(final TriFunction<CriteriaBuilder, CriteriaQuery<T>, Root<T>, Predicate> func) {
        return getEntityManager().createQuery(constructQuery(func)).getResultList();
    }

    @Override
    @Transactional
    public void remove(final long id) {
        final T entity = findById(id);
        getEntityManager().remove(entity);
    }

    @Override
    public CriteriaQuery<T> constructQuery(final TriFunction<CriteriaBuilder, CriteriaQuery<T>, Root<T>, Predicate> func) {
        final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<T> cq = cb.createQuery(persistentClass);
        final Root<T> root = cq.from(persistentClass);
        return cq.select(root).where(func.apply(cb, cq, root));
    }
}

package com.hiraeth.core.persistence.generic.dao.impl;

import com.hiraeth.core.persistence.generic.dao.AbstractGenericDao;
import com.hiraeth.core.persistence.generic.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class HiraethGenericDao<T extends AbstractEntity> extends AbstractGenericDao<T> {

    @PersistenceContext(unitName = "hiraeth")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}

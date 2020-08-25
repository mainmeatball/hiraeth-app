package com.hiraeth.backend.dao;

import com.hiraeth.backend.entity.NameAwareEntity;
import com.hiraeth.core.generic.dao.impl.HiraethGenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao extends HiraethGenericDao<NameAwareEntity> {
}

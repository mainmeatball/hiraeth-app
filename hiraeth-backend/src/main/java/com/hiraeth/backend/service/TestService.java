package com.hiraeth.backend.service;

import com.hiraeth.backend.dao.TestDao;
import com.hiraeth.backend.entity.NameAwareEntity;
import com.hiraeth.core.persistence.generic.service.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TestService extends GenericServiceImpl<NameAwareEntity, TestDao> {
}

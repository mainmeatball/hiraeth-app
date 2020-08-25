package com.hiraeth.core.generic.dao;

import com.hiraeth.core.generic.dao.impl.MockEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:mock.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GenericDaoTest {

    @Autowired
    private GenericDao<MockEntity> mockDao;

    @Test
    public void saveEntity() {
        final String name = "testBean";
        final MockEntity entity = new MockEntity(name);
        final MockEntity savedEntity = mockDao.save(entity);

        final List<MockEntity> entities = mockDao.findAll();
        assertEquals(name, savedEntity.getName());
        assertEquals(1, entities.size());

        mockDao.remove(savedEntity.getId());
    }

    @Test
    public void updateEntity() {
        final String name = "testBean";
        final MockEntity entity = new MockEntity(name);
        final MockEntity savedEntity = mockDao.save(entity);

        final List<MockEntity> entities = mockDao.findAll();
        assertEquals(name, savedEntity.getName());
        assertEquals(1, entities.size());

        final String updatedName = "updatedTestBean";
        final MockEntity updatedEntity = new MockEntity(updatedName);
        updatedEntity.setId(savedEntity.getId());
        final MockEntity savedUpdatedEntity = mockDao.save(updatedEntity);

        final List<MockEntity> entitiesAfterUpdate = mockDao.findAll();
        assertEquals(updatedName, savedUpdatedEntity.getName());
        assertEquals(1L, entitiesAfterUpdate.size());

        mockDao.remove(savedUpdatedEntity.getId());
    }

    @Test
    public void removeEntity() {
        final String name = "testBean";
        final MockEntity entity = new MockEntity(name);
        final MockEntity savedEntity = mockDao.save(entity);

        final List<MockEntity> entities = mockDao.findAll();
        assertEquals(name, savedEntity.getName());
        assertEquals(1L, entities.size());

        mockDao.remove(savedEntity.getId());

        final MockEntity foundEntity = mockDao.findById(1L);

        assertNull(foundEntity);
    }

    @Test
    public void findEntityByNameUsingMap() {
        final String name = "testBean";
        final MockEntity entity = new MockEntity(name);
        final MockEntity savedEntity = mockDao.save(entity);
        assertEquals(name, savedEntity.getName());

        final MockEntity secondEntity = new MockEntity(name);
        final MockEntity secondSavedEntity = mockDao.save(secondEntity);

        final Map<String, Object> params = Map.of("name", name);

        final List<MockEntity> entities = mockDao.find(params);
        assertEquals(2L, entities.size());

        mockDao.remove(savedEntity.getId());
        mockDao.remove(secondSavedEntity.getId());
    }

    @Test
    public void findEntityByNameParam() {
        final String name = "testBean";
        final MockEntity entity = new MockEntity(name);
        final MockEntity savedEntity = mockDao.save(entity);
        assertEquals(name, savedEntity.getName());

        final MockEntity secondEntity = new MockEntity(name);
        final MockEntity secondSavedEntity = mockDao.save(secondEntity);

        final List<MockEntity> entities = mockDao.findBy("name", name);
        assertEquals(2L, entities.size());

        mockDao.remove(savedEntity.getId());
        mockDao.remove(secondSavedEntity.getId());
    }

    @Test
    public void findEntityByNameWithCriteria() {
        final String name = "testBean";
        final MockEntity entity = new MockEntity(name);
        final MockEntity savedEntity = mockDao.save(entity);
        assertEquals(name, savedEntity.getName());

        final MockEntity secondEntity = new MockEntity(name);
        final MockEntity secondSavedEntity = mockDao.save(secondEntity);

        final List<MockEntity> entities = mockDao.findByCondition((cb, cq, root) ->
                cb.equal(root.get("name"), name)
        );

        assertEquals(2L, entities.size());

        mockDao.remove(savedEntity.getId());
        mockDao.remove(secondSavedEntity.getId());
    }
}

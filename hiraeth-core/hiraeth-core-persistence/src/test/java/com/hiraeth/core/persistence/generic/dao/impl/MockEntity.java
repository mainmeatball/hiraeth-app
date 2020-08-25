package com.hiraeth.core.persistence.generic.dao.impl;

import com.hiraeth.core.persistence.generic.entity.AbstractEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hiraeth")
public class MockEntity extends AbstractEntity {

    private String name;

    public MockEntity() {
    }

    public MockEntity(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}

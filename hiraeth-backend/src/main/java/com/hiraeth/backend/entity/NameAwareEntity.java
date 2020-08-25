package com.hiraeth.backend.entity;

import com.hiraeth.core.generic.entity.AbstractEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hiraeth")
public class NameAwareEntity extends AbstractEntity {

    private String name;

    public NameAwareEntity() {
    }

    public NameAwareEntity(String name) {
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

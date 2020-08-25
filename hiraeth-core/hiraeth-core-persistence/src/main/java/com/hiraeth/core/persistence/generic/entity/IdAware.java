package com.hiraeth.core.persistence.generic.entity;

public interface IdAware {
    Long getId();

    default boolean isNew() {
        return getId() == null;
    }
}

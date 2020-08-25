package com.hiraeth.core.generic.entity;

public interface IdAware {
    Long getId();

    default boolean isNew() {
        return getId() == null;
    }
}

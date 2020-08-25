package com.hiraeth.core.generic.entity;

import com.hiraeth.core.generic.reflection.HiraethReflectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable, IdAware {

    protected Long id;

    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final Map<String, Object> getters = HiraethReflectionUtils.invokeAllGetters(this);
        final String fields = getters.entrySet()
                .stream()
                .map(e -> e.getKey() + " = " + e.getValue().toString())
                .collect(Collectors.joining(","));
        return this.getClass().getSimpleName() + " {" + fields + '}';
    }
}

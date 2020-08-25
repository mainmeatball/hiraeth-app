package com.hiraeth.core.utils.reflection;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class HiraethReflectionUtils {
    public static <T> Map<String, Object> invokeAllGetters(final T entity) {
        final Class<?> clazz = entity.getClass();
        final Map<String, Object> map = new HashMap<>();
        try {
            Stream.of(Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors())
                    .filter(pd -> Objects.nonNull(pd.getReadMethod()))
                    .forEach(pd -> {
                        try {
                            Object value = pd.getReadMethod().invoke(entity);
                            if (value != null) {
                                map.put(pd.getName(), value);
                            }
                        } catch (final Exception e) {
                            throw new IllegalArgumentException("An error occurred during invocation of getter method of " + pd.getName() + " field", e);
                        }
                    });
        } catch (final IntrospectionException e) {
            throw new IllegalArgumentException("An error occurred during inspecting getters of " + clazz.getName() + " class", e);
        }
        return map;
    }
}

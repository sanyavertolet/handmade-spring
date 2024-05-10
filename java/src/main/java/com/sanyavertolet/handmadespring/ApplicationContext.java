package com.sanyavertolet.handmadespring;

import com.sanyavertolet.handmadespring.annotations.Singleton;
import com.sanyavertolet.handmadespring.config.Config;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    @Setter private ObjectFactory factory;
    @Getter private final Config config;
    private final Map<Class<?>, Object> cache = new ConcurrentHashMap<>();

    public ApplicationContext(Config config) {
        this.config = config;
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> type) {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }

        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        T obj = factory.createObject(implClass);

        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, obj);
        }
        return obj;
    }
}

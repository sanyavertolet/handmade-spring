package com.sanyavertolet.handmadespring.config;

import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

@SuppressWarnings({"rawtypes", "unchecked"})
public class JavaConfig implements Config {
    private final Reflections scanner;
    private final Map<Class, Class> interfaceToImplementation;

    public JavaConfig(String packageName, Map<Class, Class> interface2Impl) {
        scanner = new Reflections(packageName);
        interfaceToImplementation = interface2Impl;
    }

    @Override
    public <T> Class<T> getImplClass(Class<T> interfaceType) throws RuntimeException {
        return interfaceToImplementation.computeIfAbsent(interfaceType, aClass -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(interfaceType);
            if (classes.size() != 1) {
                throw new RuntimeException("Could not determine single interface ${interfaceType.simpleName} implementation");
            }
            return classes.iterator().next();
        });
    }

    @Override
    public Reflections getScanner() {
        return scanner;
    }
}

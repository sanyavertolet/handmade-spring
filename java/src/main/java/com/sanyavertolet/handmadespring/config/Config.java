package com.sanyavertolet.handmadespring.config;

import org.reflections.Reflections;

public interface Config {
    <T> Class<T> getImplClass(Class<T> interfaceType);

    Reflections getScanner();
}

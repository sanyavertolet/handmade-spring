package com.sanyavertolet.handmadespring;

import com.sanyavertolet.handmadespring.beanpostprocessor.BeanPostProcessor;
import com.sanyavertolet.handmadespring.proxypostprocessor.ProxyPostProcessor;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {
    private final ApplicationContext context;
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    private final List<ProxyPostProcessor> proxyPostProcessors = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends BeanPostProcessor> aClass : context.getConfig().getScanner().getSubTypesOf(BeanPostProcessor.class)) {
            beanPostProcessors.add(aClass.getDeclaredConstructor().newInstance());
        }
        for (Class<? extends ProxyPostProcessor> aClass : context.getConfig().getScanner().getSubTypesOf(ProxyPostProcessor.class)) {
            proxyPostProcessors.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {
        T object = create(implClass);
        configure(object);
        invokeInit(implClass, object);
        object = wrapWithProxy(implClass, object);
        return object;
    }

    @SuppressWarnings("unchecked")
    private <T> T wrapWithProxy(Class<T> implClass, T object) {
        for (ProxyPostProcessor proxyPostProcessor : proxyPostProcessors) {
            object = (T) proxyPostProcessor.replaceWithProxyIfNeeded(object, implClass);
        }
        return object;
    }

    @SneakyThrows
    private static <T> T create(Class<T> implClass) {
        return implClass.getDeclaredConstructor().newInstance();
    }

    private <T> void configure(T object) {
        beanPostProcessors.forEach(beanPostProcessor -> beanPostProcessor.configure(object, context));
    }

    @SneakyThrows
    private static <T> void invokeInit(Class<T> implClass, T object) {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(object);
            }
        }
    }
}

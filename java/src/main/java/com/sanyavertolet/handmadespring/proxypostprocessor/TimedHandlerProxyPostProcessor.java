package com.sanyavertolet.handmadespring.proxypostprocessor;

import com.sanyavertolet.handmadespring.annotations.Timed;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SuppressWarnings("unused")
public class TimedHandlerProxyPostProcessor implements ProxyPostProcessor {
    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object replaceWithProxyIfNeeded(Object obj, Class implClass) {
        if (implClass.isAnnotationPresent(Timed.class)) {
            if (implClass.getInterfaces().length == 0) {
                System.out.println("Cannot process classes, only proxying by interface implementation is implemented");
                return obj;
            }
            return Proxy.newProxyInstance(
                    implClass.getClassLoader(),
                    implClass.getInterfaces(),
                    (proxy, method, args) -> getInvocationHandlerLogic(obj, method, args)
            );
        }
        return obj;
    }

    private static Object getInvocationHandlerLogic(Object obj, Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        long before = System.currentTimeMillis();
        Object result = method.invoke(obj, args);
        long after = System.currentTimeMillis();
        System.out.println("Took + " + (after - before) + " millis");
        return result;
    }
}

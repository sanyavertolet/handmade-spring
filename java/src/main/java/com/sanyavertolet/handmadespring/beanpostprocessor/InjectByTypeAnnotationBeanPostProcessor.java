package com.sanyavertolet.handmadespring.beanpostprocessor;

import com.sanyavertolet.handmadespring.ApplicationContext;
import com.sanyavertolet.handmadespring.annotations.InjectByType;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class InjectByTypeAnnotationBeanPostProcessor implements BeanPostProcessor {
    @SneakyThrows
    @Override
    public void configure(Object obj, ApplicationContext context) {
        for (Field declaredField : obj.getClass().getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(InjectByType.class)) {
                declaredField.setAccessible(true);
                Object object = context.getObject(declaredField.getType());
                declaredField.set(obj, object);
            }
        }
    }
}

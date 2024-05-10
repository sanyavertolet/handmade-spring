package com.sanyavertolet.handmadespring.beanpostprocessor;

import com.sanyavertolet.handmadespring.ApplicationContext;
import com.sanyavertolet.handmadespring.annotations.InjectProperty;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@SuppressWarnings("unused")
public class InjectPropertyAnnotationBeanPostProcessor implements BeanPostProcessor {
    @SuppressWarnings("FieldMayBeFinal")
    private Map<String, String> properties;

    @SneakyThrows
    public InjectPropertyAnnotationBeanPostProcessor() {
        String path = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("application.properties")).getPath();
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        properties = lines.map(line -> line.split("=")).collect(toMap(array -> array[0], array -> array[1]));
    }

    @Override
    @SneakyThrows
    public void configure(Object obj, ApplicationContext context) {
        Class<?> implClass = obj.getClass();
        for (Field declaredField : implClass.getDeclaredFields()) {
            InjectProperty annotation = declaredField.getAnnotation(InjectProperty.class);
            if (annotation != null) {
                String value = annotation.value().isEmpty() ? properties.get(declaredField.getName()) : properties.get(annotation.value());
                declaredField.setAccessible(true);
                declaredField.set(obj, value);
            }
        }
    }
}

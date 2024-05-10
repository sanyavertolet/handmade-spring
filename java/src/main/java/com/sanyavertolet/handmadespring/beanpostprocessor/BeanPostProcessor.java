package com.sanyavertolet.handmadespring.beanpostprocessor;

import com.sanyavertolet.handmadespring.ApplicationContext;

public interface BeanPostProcessor {
    void configure(Object obj, ApplicationContext context);
}

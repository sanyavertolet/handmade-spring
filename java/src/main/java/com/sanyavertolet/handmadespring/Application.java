package com.sanyavertolet.handmadespring;

import com.sanyavertolet.handmadespring.config.JavaConfig;

import java.util.Map;

@SuppressWarnings("rawtypes")
public class Application {
    public static ApplicationContext run(String packageName, Map<Class, Class> interface2Impl) {
        JavaConfig javaConfig = new JavaConfig(packageName, interface2Impl);
        ApplicationContext context = new ApplicationContext(javaConfig);
        ObjectFactory objectFactory = new ObjectFactory(context);
        // todo: init all singletons which are not lazy
        context.setFactory(objectFactory);
        // todo: event ContextRefreshed
        return context;
    }
}

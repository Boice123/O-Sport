package com.jsj141.osport;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 用来获取springcontext
 */
@Component
@Lazy(false)
public class SpringContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return SpringContext.applicationContext;
    }

    public static Object getBean(String name) {
        return SpringContext.getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return SpringContext.getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return SpringContext.getApplicationContext().getBean(name, clazz);
    }
}

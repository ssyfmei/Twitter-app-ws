package com.yifengblog.twitter.ws;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {
    private static ApplicationContext CONTEXT;
    
    public static <T> T getBean(String beanName, Class<T> requiredType) {
        return CONTEXT.getBean(beanName, requiredType);
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }
}
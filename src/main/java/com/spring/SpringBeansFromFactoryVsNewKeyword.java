package com.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringBeansFromFactoryVsNewKeyword {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigOne.class);
    }
}

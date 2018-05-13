package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class ConfigurationCSTest {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        System.out.println(context.getBean("config"));
        context.registerShutdownHook();
    }
}

@Configuration
@ComponentScan(basePackages = "com")
class Config {

    public Config() {
        System.out.println("called once");
    }

    @Bean
    public Object x() {
        final Object o = new Object();
        System.out.println("now creating bean " + o + " only called once");
        return o;
    }

    public String toString() {
        return "I am also a component";
    }
}

@Component
class ComponentY {
    @Autowired
    public ComponentY(Object x) {
        System.out.println("now printing bean " + x);
    }
}



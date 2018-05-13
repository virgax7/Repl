package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com")
class ConfigTwo {

    public ConfigTwo() {
        System.out.println("ConfigTwo.ConfigTwo " + this);
    }

    @Bean
    public Object x() {
        final Object o = new Object();
        System.out.println("ConfigTwo.x() " + o);
        return o;
    }

    @Bean
    public Object y() {
        final Object o = new Object();
        System.out.println("ConfigTwo.y() " + o);
        return o;
    }
}

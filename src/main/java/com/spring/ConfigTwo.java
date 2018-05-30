package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = "com")
@PropertySource("classpath:some.properties")
class ConfigTwo {

    @Autowired
    public Environment env;

    @Value("${name}")
    public String name;

    @Value("${lastname}")
    public String lastname;

    public ConfigTwo() {
        System.out.println("ConfigTwo.ConfigTwo " + this);
    }

    // can't do this
//    @Bean
//    public Environment environment() {
//        return env;
//    }

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

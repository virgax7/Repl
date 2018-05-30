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
@PropertySource("classpath:someother.properties")
class ConfigOne {

    @Autowired
    public Environment env;

    @Value("${name}")
    public String name;

    @Value("${lastname}")
    public String lastname;

    public ConfigOne() {
        System.out.println("ConfigOne.ConfigOne " + this);
    }

    @Bean
    public Object x() {
        final Object o = new Object();
        System.out.println("ConfigOne.x " + o);
        return o;
    }

    @Bean
    public ConfigOne configOnee() {
        final ConfigOne configOne = new ConfigOne();
        System.out.println("ConfigOne.configOnee " + configOne);
        return configOne;
    }

    @Bean
    public String fooX() {
        return "fooX";
    }
}

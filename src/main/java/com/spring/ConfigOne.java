package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com")
class ConfigOne {

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
}

package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class ComponentY {
    @Autowired
    public ComponentY(Object x) {
        System.out.println("ComponentY.ComponentY " + x);
    }

    @Bean
    public ConfigOne configOneee() {
        final ConfigOne configOne = new ConfigOne();
        System.out.println("ComponentY.configOnee " + configOne);
        return configOne;
    }
}

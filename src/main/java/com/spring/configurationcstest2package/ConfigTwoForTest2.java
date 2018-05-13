package com.spring.configurationcstest2package;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan()
public class ConfigTwoForTest2 {
    @Bean
    public String x() {
        return "x";
    }

}

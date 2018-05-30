package net;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "net")
@PropertySource("classpath:someother.properties")
public class ConfigNet {
    public ConfigNet() {
        System.out.println("ConfigNet constructor");
    }
    @Bean
    public Integer someNumber() {
        return 19;
    }
}

package com.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationCSTestOne {
    /*
    Spring handles instantiating beans only once.
    For example ConfigTwo.x() never get's called because @Bean tag has the same method name..
    This means ConfigTwo.y() will get called along with ConfigOne.configOnee
    However, in the case of ConfigOne.configOnee, it isn't the proxied class as the toString method will just print out
    ConfigOne.configOnee com.spring.ConfigOne@7486b455, unlike the instance made from component scan on itself by registering
    it with the AnnotationConfigApplicationContext(ConfigOne.class). This proxied instead will print out
    ConfigOne.ConfigOne com.spring.ConfigOne$$EnhancerBySpringCGLIB$$57fe7849@3cce5371
    and similary for the ConfigTwo
    ConfigTwo.ConfigTwo com.spring.ConfigTwo$$EnhancerBySpringCGLIB$$52b87aef@37313c65
    ComponentY.configOneee will also print that it's a regular non proxied instance

    Again with the singleton instantiation, even though ConfigTwo also scans the com package recursively, it won't
    reinstantiate any already instantiated beans such as ConfigOne and ConfigTwo, although they are infact
    meta annotated with @Component
     */
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigOne.class);
        System.out.println(context.getBean("configOne"));
        context.registerShutdownHook();
    }
}



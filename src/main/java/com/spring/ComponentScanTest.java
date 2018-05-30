package com.spring;

import net.ConfigNet;
import net.SomeComponent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
    ConfigOne scans everything under com
    it also scans ConfigThree which scans everything under net
    so can I retrieve the beans scanned by ConfigThree using @Component?

    Answer yes
 */
public class ComponentScanTest {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigOne.class);
        // remember configuration is also a component... (meta annotated with it)
        final ConfigNet configNet = (ConfigNet) context.getBean("configNet");
        System.out.println(configNet.someNumber());
        final SomeComponent someComponent = (SomeComponent) context.getBean("someComponent");
        System.out.println(someComponent.getName());
    }
}

package com.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
This one is going to test how far a property source is available to
Also what happens with conflicting props. One propertyresource specified in configuration bean specified in the root context and
another that is from a scanned configuration
 */
// results
// since configone also scans configtwo and its sibling annotations, all things are initalized, including properties from someother.properties
// configOne can also use lastname due to reason explained above.
// the properties of the original configuration take precedence though, so name is always pyoung
public class PropertySourceTest {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigOne.class);
        final ConfigTwo configTwo = (ConfigTwo) context.getBean("configTwo");
        System.out.println(configTwo.env);
        System.out.println(configTwo.name);
        final ConfigOne configOne = (ConfigOne) context.getBean("configOne");
        System.out.println(configOne.env);
        System.out.println(configOne.env == configTwo.env);
        System.out.println(configTwo.name);
        System.out.println(configTwo.lastname);
        System.out.println(configOne.lastname);
    }
}

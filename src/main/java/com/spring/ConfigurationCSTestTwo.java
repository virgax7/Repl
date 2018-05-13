package com.spring;

import com.spring.configurationcstest2package.ConfigTwoForTest2;
import com.spring.configurationcstest2package.Foo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationCSTestTwo {
    // the point here is that instantiated classes are not proxied instances. They are just injected by a higher level component(the spring container)...
    // also non factoried bean fields don't get their autowired fields set
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigTwoForTest2.class);
        System.out.println(context.getBean("foo") + " from " + ((Foo) context.getBean("foo")).superToString());
        // prints x + com.spring.configurationcstest2package.Foo@1c9b0314
        Foo x = new Foo();
        System.out.println(x.toString() + " from " + x.superToString());
        // prints null + com.spring.configurationcstest2package.Foo@49c90a9c
        context.registerShutdownHook();
    }
}

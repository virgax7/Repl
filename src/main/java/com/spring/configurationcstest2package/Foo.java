package com.spring.configurationcstest2package;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Foo {
    @Autowired
    private String x;

    @Override
    public String toString() {
        return x;
    }

    public String superToString() {
        return super.toString();
    }
}

package net;

import org.springframework.stereotype.Component;

@Component
public class SomeComponent {
    public SomeComponent() {
        System.out.println("SomeComponent constructor");
    }

    public String getName() {
        return "someComponent";
    }
}

package com.example.toby.di;

import java.util.Objects;

// spring container interface
public class HelloController3 {

    private final HelloService helloService;

    public HelloController3(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello3(String name) {

        return helloService.sayHello2(Objects.requireNonNull(name));
    }
}

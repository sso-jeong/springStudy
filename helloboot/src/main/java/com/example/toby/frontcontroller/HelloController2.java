package com.example.toby.frontcontroller;

import java.util.Objects;


public class HelloController2 {

    public String hello2(String name) {
        SimpleHelloService helloService = new SimpleHelloService();

        return helloService.sayHello(Objects.requireNonNull(name));
    }
}

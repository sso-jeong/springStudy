package com.example.toby.di;

public class SimpleHelloService2 implements HelloService {
    @Override
    public String sayHello2(String name){
        return "Hello " + name;
    }
}

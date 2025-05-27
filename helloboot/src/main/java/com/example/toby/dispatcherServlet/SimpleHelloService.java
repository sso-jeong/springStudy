package com.example.toby.dispatcherServlet;

public class SimpleHelloService implements HelloService {

    @Override
    public String sayHello(String name){
        return "Hello " + name;
    }
}

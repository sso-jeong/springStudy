package com.example.toby.component;

import com.example.toby.di.HelloService;
import org.springframework.stereotype.Component;

@Component
public class SimpleHelloService3 implements HelloService {
    @Override
    public String sayHello2(String name){
        return "Hello " + name;
    }
}

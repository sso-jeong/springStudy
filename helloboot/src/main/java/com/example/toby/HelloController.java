package com.example.toby;

import com.example.toby.dispatcherServlet.HelloService;

public class HelloController {

    private final HelloService service;

    public HelloController(HelloService service) {
        this.service = service;
    }

    public String hello(String name) {

        return service.sayHello(name);
    }
}

package com.example.toby;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


public class HelloController2 {

    public String hello2(String name) {
        return "hello2 " + name;
    }
}

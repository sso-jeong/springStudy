package com.example.toby.dispatcherServlet;

import com.example.toby.di.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RequestMapping("/hello4")
public class HelloController4 {
    private final HelloService helloService;

    public HelloController4(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    @ResponseBody //리턴 값을 View로 보내지 않고, HTTP 응답 본문(Response Body)에 바로 출력
    public String hello4(String name) {
        return helloService.sayHello2(Objects.requireNonNull(name));
    }
}

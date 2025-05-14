package com.example.toby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* 방법1. 스프링 컨테이너를 사용하는 가장 기본적임 */
@SpringBootApplication
public class HellobootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HellobootApplication.class, args);
    }

}
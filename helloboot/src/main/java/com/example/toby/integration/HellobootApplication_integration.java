package com.example.toby.integration;

import com.example.toby.di.HelloService;
import com.example.toby.di.SimpleHelloService2;
import com.example.toby.dispatcherServlet.HelloController4;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// Servlet container를 만들고 서블릿을 초기화하는 작업을 스프링 컨테이너가 초기화되는 과정 중에 일어나도록 수정
@Configuration
public class HellobootApplication_integration {

    // 스프링 컨테이너가 이걸 쓴다
    // 팩토리 메소드
    @Bean
    public HelloController4 HelloController4(HelloService helloService){
        return new HelloController4(helloService);
    }

    @Bean
    public HelloService helloService() {
        return new SimpleHelloService2();
    }

    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext appCon = new AnnotationConfigWebApplicationContext(){
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory sf = new TomcatServletWebServerFactory();
                WebServer webServer = sf.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",
                            new DispatcherServlet(this)
                    ).addMapping("/*"); // 모든 요청을 받는다
                });
                webServer.start();
            }
        };
        appCon.register(HellobootApplication_integration.class);
        appCon.refresh(); // 스프링컨테이너 초기화 작업


    }
}
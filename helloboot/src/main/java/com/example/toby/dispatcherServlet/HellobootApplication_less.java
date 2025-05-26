package com.example.toby.dispatcherServlet;

import com.example.toby.di.HelloController3;
import com.example.toby.di.SimpleHelloService2;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// HellobootApplication_spring_container 안에는 서블릿 코드 안에 하드코딩이 되어있음
public class HellobootApplication_less {
    public static void main(String[] args) {
        // 1. spring container를 만드는 작업 후
        // 2. Servlet container 를 코드로 실행하면서 Servlet 을 등록하는 작업
        GenericWebApplicationContext appCon = new GenericWebApplicationContext();
        appCon.registerBean(HelloController4.class); // bean 등록
        appCon.registerBean(SimpleHelloService2.class); // class type bean 등록
        appCon.refresh(); // 스프링컨테이너 초기화 작업

        ServletWebServerFactory sf = new TomcatServletWebServerFactory();
        WebServer webServer = sf.getWebServer(servletContext -> {
            servletContext.addServlet("dispatcherServlet",
                    new DispatcherServlet(appCon)
                    ).addMapping("/*"); // 모든 요청을 받는다
        });
        webServer.start();
    }
}
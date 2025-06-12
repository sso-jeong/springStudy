package com.example.toby.integration;

import com.example.toby.di.SimpleHelloService2;
import com.example.toby.dispatcherServlet.HelloController4;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// Servlet container를 만들고 서블릿을 초기화하는 작업을 스프링 컨테이너가 초기화되는 과정 중에 일어나도록 수정
public class HellobootApplication_GenericIntegration {

    public static void main(String[] args) {
        //GenericWebApplicationContext 은 Configuration 정보를 읽을 수 없음
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
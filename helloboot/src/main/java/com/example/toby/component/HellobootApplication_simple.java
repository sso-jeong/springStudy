package com.example.toby.component;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// 팩토리 메소드를 사용하지 않고 간결하게 Bean을 등록하는 방법
@Configuration
// Component Bean을 찾아서 등록하고 컨테이너로 전달됨
@ComponentScan
public class HellobootApplication_simple {

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
        appCon.register(HellobootApplication_simple.class);
        appCon.refresh(); // 스프링컨테이너 초기화 작업


    }
}
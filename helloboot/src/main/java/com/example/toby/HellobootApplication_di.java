package com.example.toby;

import com.example.toby.dispatcherServlet.SimpleHelloService;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

// 방법1. di 기본
// http -v :8080/"hello"
public class HellobootApplication_di {
    public static void main(String[] args) throws Exception {
        // 1. spring 컨테이너 생성
        GenericApplicationContext appCon = new GenericApplicationContext();
        appCon.registerBean(HelloController.class);
        appCon.registerBean(SimpleHelloService.class);
        appCon.refresh();

        ServletWebServerFactory sf = new TomcatServletWebServerFactory();

        WebServer webServer = sf.getWebServer(servletContext -> {
            servletContext.addServlet("frontcontroller", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {

                        String name = req.getParameter("name");
                        // 1. 매핑작업이 하드코딩되어있음
                        // 2. 파라미터가 하나뿐임
                        HelloController hCtr = appCon.getBean(HelloController.class); // DI+싱글톤
                        String ret = hCtr.hello(name);

                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println(ret);
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            }).addMapping("/*"); // 모든 요청을 받는다
        });
        webServer.start();


    }
}
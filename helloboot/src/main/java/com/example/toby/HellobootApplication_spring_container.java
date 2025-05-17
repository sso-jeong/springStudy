package com.example.toby;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication_spring_container {
    public static void main(String[] args) {
        // spring container
        GenericApplicationContext appCon = new GenericApplicationContext();
       // appCon.registerBean(HelloController2.class); // bean 등록
        appCon.registerBean(HelloController3.class); // bean 등록
        appCon.registerBean(SimpleHelloService2.class); // class type bean 등록
        appCon.refresh();

        ServletWebServerFactory sf = new TomcatServletWebServerFactory();
        WebServer webServer = sf.getWebServer(servletContext -> {
            servletContext.addServlet("frontcontroller", new HttpServlet() {
                        @Override
                        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                            if(req.getRequestURI().equals("/hello3") && req.getMethod().equals(HttpMethod.GET.name())){

                                String name = req.getParameter("name");

                                HelloController3 hCtr2 = appCon.getBean(HelloController3.class); // DI+싱글톤
                                String ret = hCtr2.hello3(name);

                                resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                                resp.getWriter().println(ret);
                            }
                            else {
                                resp.setStatus(HttpStatus.NOT_FOUND.value());
                            }
                        }
                    }).addMapping("/*"); // 모든 요청을 받는다
        });
        webServer.start();
    }
}
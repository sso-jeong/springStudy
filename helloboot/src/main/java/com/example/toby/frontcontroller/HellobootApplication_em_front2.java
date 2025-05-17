package com.example.toby.frontcontroller;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 프론트컨트롤러에서 모든걸 처리할 수 없으니 컨트롤러에 역할을 위임한다.
public class HellobootApplication_em_front2 {
    public static void main(String[] args) {

        ServletWebServerFactory sf = new TomcatServletWebServerFactory();
        WebServer webServer = sf.getWebServer(servletContext -> {

            HelloController2 hctr = new HelloController2();

            servletContext.addServlet("frontcontroller", new HttpServlet() {
                        @Override
                        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                            if(req.getRequestURI().equals("/hello2") && req.getMethod().equals(HttpMethod.GET.name())){

                                String ret = hctr.hello2("name");

                                resp.setStatus(HttpStatus.OK.value());
                                resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                                resp.getWriter().println(ret);
                            }
                            else if (req.getRequestURI().equals("/user")){
                                //
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
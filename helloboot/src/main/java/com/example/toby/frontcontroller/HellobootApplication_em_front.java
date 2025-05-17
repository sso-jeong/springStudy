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

// 모든 서블릿에 공통적으로 등작하는 처리하는 코드를 중앙화된 제일 앞단에 존재하는 컨트롤러라고 이름 붙이는 객체에서
// 이 공통적인 작업을 처리하고 요청의 종류에 따라서 이 로직을 처리하는 다른 오브젝트한테
// 요청을 다시 위임해서 전달하는 방식으로 전체 애플리케이션을 돌아가게 만드는 구조
public class HellobootApplication_em_front {
    public static void main(String[] args) {
        // 빈 서블릿 띄우기: em tomcat
        ServletWebServerFactory sf = new TomcatServletWebServerFactory();
        WebServer webServer = sf.getWebServer(servletContext -> {
            servletContext.addServlet("frontcontroller", new HttpServlet() {
                        @Override
                        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                            // 인증, 보안, 다국어처리, 공통 기능

                            if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())){

                                String name = req.getParameter("name");

                                resp.setStatus(HttpStatus.OK.value());
                                resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                                resp.getWriter().println("Hello " +name);
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
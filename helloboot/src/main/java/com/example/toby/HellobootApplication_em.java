package com.example.toby;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/* 15. Containerless
public class HellobootApplication_em {
    public static void main(String[] args) {
        System.out.println("Test");
    }
}

*/

// servlet 컨테이너 만들어서 서블릿띄우기
// http -v :8080/"hello?name=Test"
public class HellobootApplication_em {
    public static void main(String[] args) {
        // 빈 서블릿 띄우기: em tomcat
        ServletWebServerFactory sf = new TomcatServletWebServerFactory();
        WebServer webServer = sf.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new HttpServlet() {
                        @Override
                        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                            String name = req.getParameter("name");

                            resp.setStatus(HttpStatus.OK.value());
                            resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                            resp.getWriter().println("Hello " +name);
                        }
                    }).addMapping("/hello");
        });
        webServer.start();
    }
}

/* 서블릿 개선 필요
* 서블릿 여러개 늘어나고 요청을 직접 받아서 수행하고 리턴하는 방식은
* 공통적인 작업이 각 서블릿 코드에 중복으로 들어간다
*
* 두번째는
* 웹 요청과 응답을 직접적으로 req res에 다뤄줘야하기 때문에
* 기본적인 서블릿만가지고 다루기엔 한계가 있었음
*
* 개선방향 프론트컨트롤ㄹ러가 나옴*/
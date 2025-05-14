package com.example.toby;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

// 방법3. 프론트 컨트롤러의 역할을 컨트롤러에 위임
// http -v :8080/"hello"
public class HellobootApplication_tomcat_front2 {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // 1. Context 생성
        // Web root 경로가 필요해서 아래 로직이 필요함
        Context context = tomcat.addContext("", new File(".").getAbsolutePath());

        HelloController ctr = new HelloController();
        // 2. Servlet 등록
        Tomcat.addServlet(context, "frontController", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

                if(req.getRequestURI().equals("/hello")) {

                    String name = req.getParameter("name");
                    String param = ctr.hello(name);

                    resp.setStatus(HttpStatus.OK.value());
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                    resp.getWriter().write(param);
                }
                else if (req.getRequestURI().equals("/user")){
                    //
                }
                else {
                    resp.setStatus(HttpStatus.NOT_FOUND.value());
                }
            }
        });

        // 3. URL 매핑
        context.addServletMappingDecoded("/*", "frontController");

        // 4. 서버 시작
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();

    }
}
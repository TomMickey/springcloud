package com.grgbanking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream servletInputStream = request.getInputStream();
        StringBuilder content = new StringBuilder();
        byte[] a = new byte[1024];
        int lens = -1;
        while ((lens = servletInputStream.read(a)) > 0) {
            content.append(new String(a, 0, lens));
        }
        String strcont = content.toString();// 内容
        System.out.println("==================");
        System.out.println(strcont);
        return "hello";
    }
}

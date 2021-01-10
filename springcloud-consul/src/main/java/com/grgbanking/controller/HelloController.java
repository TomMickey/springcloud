package com.grgbanking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    /**
     * 健康检查
     * @return
     */
    /*@RequestMapping("/health")
    public String healthCheck() {
        return "OK";
    }*/

    @RequestMapping("/getServerPort")
    public String getServerPort(HttpServletRequest request){
        String host = request.getRemoteHost();
        int port = request.getServerPort();
        return host + ":" + port;
    }
}

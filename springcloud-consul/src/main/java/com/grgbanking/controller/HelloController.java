package com.grgbanking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     * 健康检查
     * @return
     */
    @RequestMapping("/health")
    public String healthCheck() {
        return "OK";
    }
}

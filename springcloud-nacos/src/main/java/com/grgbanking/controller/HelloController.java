package com.grgbanking.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lzyang6
 * @License: (C) Copyright 2020-2020, grgbanking Corporation Limited.
 * @Contact: lzyang6@grgbanking.com
 * @Date: 2021/1/6 20:50
 * @Version: 1.0
 * @Description:
 */
@RestController
public class HelloController {

    @Value("${lzy.name}")
    private String name;

    @RequestMapping("/hello")
    public String hello(){
        return "hello " + name;
    }
}

package com.grgbanking.controller;

import com.grgbanking.annotition.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@Slf4j
@MyService()
public class HelloController {

    @RequestMapping(value = "/login",path = "")
    public String showLogin() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("当前登陆用户：" + name);
        return "login";
    }

}

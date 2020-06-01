package com.grgbanking.controller;

import com.grgbanking.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/aopController")
@RestController
public class AopController {

    @RequestMapping("/sayHello")
    public void sayHello(String name){
        User user = new User();
        user.setUsername(name);
        user.setPassword("123456788");
        System.out.println("-------");
        System.out.println(user);
        //return user;
    }
}

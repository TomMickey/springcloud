package com.grgbanking;

import org.springframework.web.bind.annotation.RestController;

@RestController(value = "")
public class HelloController {

    @Main(value = "这是主方法")
    public static void main(String[] args) {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}

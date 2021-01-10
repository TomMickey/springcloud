package com.grgbanking.test;

import com.grgbanking.entity.User;
import com.grgbanking.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("com.grgbanking.mapper")
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user = new User();
        user.setUserName("Tom");
        user.setPassword("123456");
        userMapper.insert(user);
    }
}

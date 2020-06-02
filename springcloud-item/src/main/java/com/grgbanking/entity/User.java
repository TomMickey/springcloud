package com.grgbanking.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "user.properties")
@ConfigurationProperties(prefix = "user")
public class User {

    private String id;

    private String name;

    private String password;

    private String address = "guangzhou";

    @Value("#{'address'}")
    private String email;
}

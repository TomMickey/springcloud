package com.grgbanking.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Data
@Component
@PropertySource(value = "test-console.properties")
public class Test {

    @Value("${user}")
    private String user;
}

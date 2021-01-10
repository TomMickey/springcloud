package com.grgbanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * @Author: lzyang6
 * @License: (C) Copyright 2020-2020, grgbanking Corporation Limited.
 * @Contact: lzyang6@grgbanking.com
 * @Date: 2021/1/6 20:49
 * @Version: 1.0
 * @Description:
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class NacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class);
    }
}

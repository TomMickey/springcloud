package com.grgbanking;

import com.grgbanking.netty.DiscardServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import javax.annotation.Resource;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class NettyApplication implements CommandLineRunner {

    @Resource
    private DiscardServer discardServer;

    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class);
    }
    @Override
    public void run(String... args) throws Exception {
        discardServer.run(8888);
    }
}

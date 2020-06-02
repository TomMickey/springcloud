package com.grgbanking.controller;

import com.grgbanking.entity.Item;
import com.grgbanking.entity.JdbcConfigBean;
import com.grgbanking.entity.Test;
import com.grgbanking.entity.User;
import com.grgbanking.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Value("${server.port}")
    private String port;

    @Autowired
    private User user;

    @Autowired
    private JdbcConfigBean jdbcConfigBean;

    @RequestMapping("/getItemById/{id}")
    public Item getItemById(@PathVariable("id") Long id){
        return itemService.queryItemById(id);
    }

    @RequestMapping("/getServerPort")
    public String getServerPort(){
        System.out.println("======");
        System.out.println(user);
        System.out.println(jdbcConfigBean);
        return "127.0.0.1:" + this.port;
    }

    /**
     * 健康检查
     * @return
     */
    @RequestMapping("/health")
    public String healthCheck() {
        return "OK";
    }

}

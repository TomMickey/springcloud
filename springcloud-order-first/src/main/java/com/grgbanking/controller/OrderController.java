package com.grgbanking.controller;

import com.grgbanking.entity.Order;
import com.grgbanking.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/getOrderById/{id}")
    public Order getOrderById(@PathVariable("id") String id){
        Order order = orderService.getOrderById(id);
        return order;
    }

    @RequestMapping("/getServerPort")
    public String getServerPort(){
        return orderService.getServerPort();
    }
}

package com.grgbanking.service;

import com.grgbanking.entity.Item;
import com.grgbanking.entity.Order;
import com.grgbanking.entity.OrderDetail;
import com.grgbanking.feign.FeignClientFirst;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private FeignClientFirst feignClientFirst;

    private static final Map<String, Order> ORDER_DATA = new HashMap<String, Order>();

    static {
        // 模拟数据库，构造测试数据
        Order order = new Order();
        order.setOrderId("201810300001");
        order.setCreateDate(new Date());
        order.setUpdateDate(order.getCreateDate());
        order.setUserId(1L);
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        Item item = new Item();// 此处并没有商品的数据，只是保存了商品ID，需要调用商品微服务获取
        item.setId(1L);
        orderDetails.add(new OrderDetail(order.getOrderId(), item));
        item = new Item(); // 构造第二个商品数据
        item.setId(2L);
        orderDetails.add(new OrderDetail(order.getOrderId(), item));
        order.setOrderDetails(orderDetails);
        ORDER_DATA.put(order.getOrderId(), order);
    }

    @Autowired
    private ItemService itemService;

    public Order getOrderById(String id){
        Order order =  ORDER_DATA.get(id);
        List<OrderDetail> orderDetails = order.getOrderDetails();
        for(OrderDetail orderDetail:orderDetails){
            Long itemId = orderDetail.getItem().getId();
            Item item = feignClientFirst.getItemById(itemId);
            //Item item = this.itemService.getItemById(itemId);
            orderDetail.setItem(item);
        }
        order.setOrderDetails(orderDetails);
        return order;
    }

    @HystrixCommand(fallbackMethod = "getServerPortError")
    public String getServerPort(){
        return itemService.getServerPort();
    }

    public String getServerPortError(){
        return "getServerPortError";
    }
}

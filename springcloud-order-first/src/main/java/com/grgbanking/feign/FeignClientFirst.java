package com.grgbanking.feign;

import com.grgbanking.entity.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "springcloud-item")
public interface FeignClientFirst {

    @RequestMapping("/item/getItemById/{id}")
    Item getItemById(@PathVariable("id") Long id);
}

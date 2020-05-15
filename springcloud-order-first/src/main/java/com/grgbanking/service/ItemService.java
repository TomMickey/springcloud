package com.grgbanking.service;

import com.grgbanking.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemService {

    @Autowired
    private RestTemplate restTemplate;

    //@HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item getItemById(Long id){
        return this.restTemplate.getForObject("http://springcloud-item/item/getItemById/" + id, Item.class);
    }

    public String getServerPort(){
        return this.restTemplate.getForObject("http://springcloud-item/item/getServerPort", String.class);
    }

    /**
     * 请求失败执行的方法
     * fallbackMethod的方法参数个数类型要和原方法一致
     *
     * @param id
     * @return
     */
    public Item queryItemByIdFallbackMethod(Long id) {
        return new Item(id, "查询商品信息出错!", null, null, null);
    }
}

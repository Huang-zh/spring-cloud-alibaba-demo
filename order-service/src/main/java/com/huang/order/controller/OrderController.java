package com.huang.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.huang.entity.Order;
import com.huang.entity.Product;
import com.huang.order.api.ProductClient;
import com.huang.order.service.OrderService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Huang.zh
 * @date 2020/8/14 10:27
 * @Description: 订单微服务控制器
 */
@RestController
public class OrderController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    //准备买1件商品
    @GetMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        //通过restTemplate调用商品微服务
        Product product = restTemplate.getForObject(
                "http://localhost:8081/product/" + pid, Product.class);

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.saveEntity(order);
        return order;
    }

    @GetMapping("/orderByClient/prod/{pid}")
    public Order orderByClient(@PathVariable("pid") Integer pid) {

        String url = "service-product";
        //通过restTemplate调用商品微服务
        Product product = restTemplate.getForObject(
                "http://"+url+"/product/" + pid, Product.class);

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.saveEntity(order);
        return order;
    }

    @GetMapping("/feign/prod/{pid}")
    public Order orderByFeign(@PathVariable("pid") Integer pid) {
        Product product = productClient.getByID(pid);
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
//        orderService.saveEntity(order);
        rocketMQTemplate.convertAndSend("order-topic",order);
        return order;
    }

    @GetMapping("test")
    @SentinelResource(value = "test",blockHandler = "testBlockHandler",fallback = "fallback")
    public String test(){
//        int i = 1/0;
        return "test";
    }

    public String testBlockHandler(BlockException ex){
        ex.printStackTrace();
        return "testRollback";
    }

    public String fallback(Throwable throwable) {
        throwable.printStackTrace();
        return "接口发生异常了...";
    }

    @GetMapping("testOfFeign/{pid}")
    public Object testOfFeign(@PathVariable Integer pid){
        Product product = productClient.getByID(pid);
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
//        orderService.saveEntity(order);
        return order;
    }
}

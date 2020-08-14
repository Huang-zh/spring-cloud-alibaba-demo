package com.huang.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Huang.zh
 * @date 2020/8/14 9:43
 * @Description: 商品微服务启动类
 */
@SpringBootApplication
@EntityScan("com.huang.entity")
@EnableDiscoveryClient
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class,args);
    }
}

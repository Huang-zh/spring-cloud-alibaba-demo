package com.huang.order.api;

import com.huang.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Huang.zh
 * @date 2020/8/14 15:22
 * @Description: Feign封装产品调用服务接口
 */
@FeignClient(name = "service-product")
public interface ProductClient {

    @GetMapping("/product/{pid}")
    Product getByID(@PathVariable("pid") Integer pid);
}

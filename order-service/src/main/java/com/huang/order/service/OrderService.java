package com.huang.order.service;

import com.huang.entity.Order;

/**
 * @author Huang.zh
 * @date 2020/8/14 10:23
 * @Description: 订单服务层抽象
 */
public interface OrderService {
    void saveEntity(Order order);
}

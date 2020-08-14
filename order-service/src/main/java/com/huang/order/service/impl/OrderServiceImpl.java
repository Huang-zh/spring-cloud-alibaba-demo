package com.huang.order.service.impl;

import com.huang.entity.Order;
import com.huang.order.dao.OrderDao;
import com.huang.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Huang.zh
 * @date 2020/8/14 10:25
 * @Description: 订单服务层具体实现
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;


    @Override
    public void saveEntity(Order order) {
        orderDao.save(order);
    }
}

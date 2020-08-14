package com.huang.order.dao;

import com.huang.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Huang.zh
 * @date 2020/8/14 10:21
 * @Description: 订单服务持久层抽象
 */
public interface OrderDao extends JpaRepository<Order,Long> {
}

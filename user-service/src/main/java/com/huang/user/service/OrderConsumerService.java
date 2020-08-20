package com.huang.user.service;

import com.alibaba.fastjson.JSON;
import com.huang.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author Huang.zh
 * @date 2020/8/20 16:02
 * @Description: 订单消息消费者
 */
@Service
@Slf4j
@RocketMQMessageListener(consumerGroup = "order-consumer",topic = "order-topic")
public class OrderConsumerService implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        log.info(JSON.toJSONString(order));
    }
}

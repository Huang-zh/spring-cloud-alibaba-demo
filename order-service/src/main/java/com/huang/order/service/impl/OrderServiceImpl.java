package com.huang.order.service.impl;

import com.huang.entity.Order;
import com.huang.entity.TxLog;
import com.huang.order.dao.OrderDao;
import com.huang.order.dao.TxLogDao;
import com.huang.order.service.OrderService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author Huang.zh
 * @date 2020/8/14 10:25
 * @Description: 订单服务层具体实现
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private TxLogDao txLogDao;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void createOrderBefore(Order order){
        String txId = UUID.randomUUID().toString();
        Message<Order> message = MessageBuilder.withPayload(order).setHeader("txId", txId).build();
        //发送半事务消息
        rocketMQTemplate.sendMessageInTransaction("order-topic",message,order);
    }

    @Transactional
    public void createOrder(String txId, Order order) {
        //本地事物代码
        orderDao.save(order);

        //记录日志到数据库,回查使用
        TxLog txLog = new TxLog();
        txLog.setTxLogId(txId);
        txLog.setContent("事物测试");
        txLog.setDate(new Date());
        txLogDao.save(txLog);
    }

    @Override
    public void saveEntity(Order order) {
        orderDao.save(order);
    }
}

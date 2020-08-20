package com.huang.order;

import com.huang.entity.Order;
import com.huang.entity.TxLog;
import com.huang.order.dao.TxLogDao;
import com.huang.order.service.OrderService;
import com.huang.order.service.impl.OrderServiceImpl;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Huang.zh
 * @date 2020/8/20 17:12
 * @Description: 事务消息监听类
 */
@Component
public class OrderServiceMessageListener implements RocketMQLocalTransactionListener {

    @Autowired
    private TxLogDao txLogDao;

    @Autowired
    private OrderServiceImpl orderService;

    /**
     * @Author Huang.zh
     * @Description 执行本地事务并成功返回commit后，消费者才能消费消息
     * @Date 2020/8/20 17:13
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            orderService.createOrder(String.valueOf(message.getHeaders().get("txId")),(Order)o);
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * @Author Huang.zh
     * @Description 消息回查 返回commit后，消费者才能消费消息
     * @Date 2020/8/20 17:24
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        TxLog txId = txLogDao.findById(String.valueOf(message.getHeaders().get("txId"))).get();
        if (txId != null){
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}

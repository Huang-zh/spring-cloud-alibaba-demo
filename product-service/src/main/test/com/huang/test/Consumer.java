package com.huang.test;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author Huang.zh
 * @date 2020/8/20 15:38
 * @Description: 消息消费者
 */

public class Consumer {

    public static void main(String[] args) {
        //创建消费者，并指定所在分组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("default-consumer-group");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        try {
            //订阅my-topic主题下属于product标签的queue
            consumer.subscribe("my-topic","product");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    System.out.println(consumeConcurrentlyContext.getMessageQueue().getTopic());
                    System.out.println(list);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
            System.out.println("Consumer Started.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

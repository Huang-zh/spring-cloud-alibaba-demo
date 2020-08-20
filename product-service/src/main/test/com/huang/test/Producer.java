package com.huang.test;

import com.huang.entity.Product;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Huang.zh
 * @date 2020/8/20 14:40
 * @Description: RocketMQ消息生产者测试类
 */
@SpringBootTest(classes = Product.class)
@RunWith(SpringRunner.class)
public class Producer {

    //消息生产者分组
    @Value("${mq.config.messageProducerGroup}")
    private String messageProducerGroup;
    //消息消费者分组
    @Value("${mq.config.messageConsumerGroup}")
    private String messageConsumerGroup;
    //命名服务ip
    @Value("${mq.config.nameServerHost}")
    private String nameServerHost;
    //命名服务端口
    @Value("${mq.config.nameServerPort}")
    private String nameServerPort;


    @Test
    public void test(){
        //创建一个消息生产者，并指定生产者所在的分组
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setProducerGroup(messageProducerGroup);
        producer.setNamesrvAddr(nameServerHost+":"+nameServerPort);
        try {
            producer.start();
            //消息实体
            Message message = new Message("my-topic", "product"
                    , "huangzhenghao's products".getBytes());
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //最后确保消息生产者被关闭
            producer.shutdown();
        }
    }

}

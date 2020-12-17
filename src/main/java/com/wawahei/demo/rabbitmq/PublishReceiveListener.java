package com.wawahei.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-17 14:16
 **/
@Component
public class PublishReceiveListener {

    @RabbitListener(queues = "queue_fanout1")
    public void receiveMsg1(String msg){
        System.out.println("队列1接收到消息："+msg);
    }

    @RabbitListener(queues = "queue_fanout2")
    public void receiveMsg2(String msg){
        System.out.println("队列2接收到消息："+msg);
    }

}
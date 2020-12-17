package com.wawahei.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-17 15:20
 **/
@Component
public class ReturnReceiveListener {
    @RabbitListener(queues = "queue_return")
    public void receiveMsg(String msg){
        System.out.println("return 模式接收的消息："+msg);
    }
}
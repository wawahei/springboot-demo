package com.wawahei.demo.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-17 13:46
 **/
@Component
public class WorkReceiveListener {

    @RabbitListener(queues = "queue_work")
    public void receiveMessage(String msg, Channel channel, Message message){
        // 只包含发送的消息
        System.out.println("1接收到消息：" + msg);
        // channel 通道信息
        // message 附加的参数信息
    }

    @RabbitListener(queues = "queue_work")
    public void receiveMessage2(Object obj,Channel channel,Message message){
        // 包含所有的信息
        System.out.println("2接收到消息：" + obj);
    }
}
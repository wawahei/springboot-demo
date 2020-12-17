package com.wawahei.demo.rabbitmq;

import com.wawahei.demo.bean.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-17 15:10
 **/
@Component
public class ConfimReceiveListener {
    @RabbitListener(queues = "queue_confirm")
    public void receiveMsg(User user) {
        System.out.println("接收到的消息为：" + user);
    }
}
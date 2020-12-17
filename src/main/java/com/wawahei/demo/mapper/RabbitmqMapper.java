package com.wawahei.demo.mapper;

import com.wawahei.demo.bean.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-17 13:40
 **/
@Component
public class RabbitmqMapper {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendWork(){
        for(int i=0; i<10; i++){
            rabbitTemplate.convertAndSend("queue_work","测试work模型"+i);
        }
    }

    public void sendPublish() {
        for(int i=0; i<5; i++){
//            输出时没有顺序，不需要等待，直接运行
//            rabbitTemplate.convertAndSend("exchange_fanout","","测试发布订阅模型："+i);
//            按照一定的顺序，只有确定消费者接收到消息，才会发送下一条信息，每条消息之间会有间隔时间
            rabbitTemplate.convertSendAndReceive("exchange_fanout","","测试发布订阅模型："+i);
        }
    }

    public void sendTopic() {
        for(int i=0; i<10; i++){
            if(i%2 == 0){
                rabbitTemplate.convertSendAndReceive("exchange_topic","topic.km.topic","测试发布订阅模型"+i);
            }else{
                rabbitTemplate.convertSendAndReceive("exchange_topic","topic.km","测试发布订阅模型"+i);
            }
        }
    }

    private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean b, String s) {
            if(b){
                System.out.println("confirm 消息确认成功："+correlationData.getId());
            }else {
                System.out.println("confirm 消息确认失败："+correlationData.getId() + " cause: "+s);
            }
        }
    };

    public void sendConfirm() {
        User user = new User(1,"km","123456");
//        使用confirm机制时，发送消息时最好把CorrelationData 加上，因为如果出错了，使用 CorrelationData 可以更快的定位到错误信息
        CorrelationData correlationData = new CorrelationData(System.currentTimeMillis()+"");
        rabbitTemplate.convertAndSend("queue_confirm",user,correlationData);
        rabbitTemplate.setConfirmCallback(confirmCallback);
    }


    private final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        /**
         *  return 的回调方法（找不到路由才会触发）
         * @param message 消息的相关信息
         * @param i 错误状态码
         * @param s 错误状态码对应的文本信息
         * @param s1 交换机的名字
         * @param s2 路由的key
         */
        @Override
        public void returnedMessage(Message message, int i, String s, String s1, String s2) {
            System.out.println(message);
            System.out.println(new String(message.getBody()));
            System.out.println(i);
            System.out.println(s);
            System.out.println(s1);
            System.out.println(s2);
        }
    };

    public void sendReturn() {
        rabbitTemplate.setReturnCallback(returnCallback);
//        rabbitTemplate.convertAndSend("exchange_return","good luck","测试 return 机制");
//        rabbitTemplate.convertAndSend("exchange_return","return.km.km","测试 return 机制");
         rabbitTemplate.convertAndSend("exchange_return", "return.km", "测试 return 机制");
    }
}
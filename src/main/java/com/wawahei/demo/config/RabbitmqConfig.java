package com.wawahei.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;


/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-17 13:34
 **/
@SpringBootConfiguration
public class RabbitmqConfig {

    @Bean
    public Queue queueWork1(){
        return new Queue("queue_work");
    }

    @Bean
    public Queue queueFanout1(){
        return new Queue("queue_fanout1");
    }

    @Bean
    public Queue queueFanout2(){
        return new Queue("queue_fanout2");
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("exchange_fanout");
    }

    // 将交换机和队列进行绑定
    @Bean
    public Binding bindingExchange1(){
        return BindingBuilder.bind(queueFanout1()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingExchange2(){
        return BindingBuilder.bind(queueFanout2()).to(fanoutExchange());
    }

    @Bean
    public Queue queueTopic1(){
        return new Queue("queue_topic1");
    }

    @Bean
    public Queue queueTopic2(){
        return new Queue("queue_topic2");
    }

    @Bean
    public TopicExchange exchangeTopic(){
        return new TopicExchange("exchange_topic");
    }

    @Bean
    public Binding bindTopic1(){
        return BindingBuilder.bind(queueTopic1()).to(exchangeTopic()).with("topic.#");
    }

    @Bean
    public Binding bindTopic2(){
        return BindingBuilder.bind(queueTopic2()).to(exchangeTopic()).with("topic.*");
    }

    @Bean
    public Queue queueConfirm(){
        return new Queue("queue_confirm");
    }

    @Bean
    public Queue queueReturn(){
        return new Queue("queue_return");
    }

    @Bean
    public TopicExchange exchangeReturn(){
        return new TopicExchange("exchange_return");
    }

    @Bean
    public Binding bindingReturn(){
        return BindingBuilder.bind(queueReturn()).to(exchangeReturn()).with("return.*");
    }

    // TTL 队列
    @Bean
    public Queue queueTTL(){
        Map<String,Object> map = new HashMap<>(1);
        map.put("x-message-ttl",10000);
        return new Queue("queue_ttl",true,false,false,map);
    }

    // 产生死信的队列
    @Bean
    public Queue queueDLX(){
        Map<String,Object> map = new HashMap<>(2);
        map.put("x-message-ttl",5000);
        map.put("x-dead-letter-exchange","exchange_receive");
        map.put("x-dead-letter-routing-key","receive_key");
        return new Queue("queue_dlx",true,false,false,map);
    }

    // 死信交换机
    @Bean
    public DirectExchange exchangeDLX(){
        return new DirectExchange("exchange_dlx");
    }

    // 给死信队列绑定交换机
    @Bean
    public Binding bindingDLX(){
        return BindingBuilder.bind(queueDLX()).to(exchangeDLX()).with("receive_key");
    }

    // 死信接收交换机
    @Bean
    public DirectExchange exchangeReceive(){
        return new DirectExchange("exchange_receive");
    }

    // 接收死信的队列
    @Bean
    public Queue queueReceive(){
        return new Queue("queue_receive");
    }

    // 将交换机与队列绑定
    @Bean
    public Binding bindingReceive(){
        return BindingBuilder.bind(queueReceive()).to(exchangeReceive()).with("receive_key");
    }

}
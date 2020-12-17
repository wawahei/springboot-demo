package com.wawahei.demo.controller.rabbitmq;

import com.wawahei.demo.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-17 13:37
 **/
@RestController
public class RabbitmqController {

    @Autowired
    private RabbitmqService rabbitmqService;

    @RequestMapping("/sendWork")
    public String sendWork(){
        rabbitmqService.sendWork();
        return "发送成功";
    }

    @RequestMapping("/sendPublish")
    public String sendPublish() {
        rabbitmqService.sendPublish();
        return "发送成功...";
    }

    @RequestMapping("/sendTopic")
    public String sendTopic() {
        rabbitmqService.sendTopic();
        return "发送成功...";
    }

    @RequestMapping("/sendConfirm")
    public String sendConfirm() {
        rabbitmqService.sendConfirm();
        return "发送成功...";
    }

    @RequestMapping("/sendReturn")
    public String sendReturn() {
        rabbitmqService.sendReturn();
        return "发送成功...";
    }
}
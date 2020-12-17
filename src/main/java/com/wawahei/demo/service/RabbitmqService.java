package com.wawahei.demo.service;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-17 13:38
 **/
public interface RabbitmqService {
    void sendWork();

    void sendPublish();

    void sendTopic();

    void sendConfirm();

    void sendReturn();
}

package com.wawahei.demo.service.impl;

import com.wawahei.demo.mapper.RabbitmqMapper;
import com.wawahei.demo.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-17 13:39
 **/
@Service
public class RabbitmqServiceImpl implements RabbitmqService{

    @Autowired
    private RabbitmqMapper rabbitmqMapper;

    @Override
    public void sendWork() {
        rabbitmqMapper.sendWork();
    }

    @Override
    public void sendPublish() {
        rabbitmqMapper.sendPublish();
    }

    @Override
    public void sendTopic() {
        rabbitmqMapper.sendTopic();
    }

    @Override
    public void sendConfirm() {
        rabbitmqMapper.sendConfirm();
    }

    @Override
    public void sendReturn() {
        rabbitmqMapper.sendReturn();
    }
}
package com.notificationresponse.services;

import com.notificationresponse.controller.NotificationResponseController;
import com.notificationresponse.model.NotificationResponse;
import com.notificationresponse.model.dto.NotificationResponseDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class NotificationResponseParser {
    private final RedisProducer redisProducer;
    private final RestConsumer restConsumer;

    @Autowired
    public  NotificationResponseParser(RedisProducer redisProducer, RestConsumer restConsumer){
        this.redisProducer=redisProducer;
        this.restConsumer = restConsumer;
    }
    public void parseResponse(NotificationResponseDTO dto){
        switch (dto.getAction()){
            case no -> redisProducer.sendMessage(dto.getType()+"", dto.getId()+"");
            case verify -> {
                restConsumer.updateMessage(dto.getMessage()+" "+new Random().nextInt(10000), dto.getId());
                redisProducer.sendMessage(dto.getType()+"",dto.getId()+"");
            }
            case password -> {
                restConsumer.updateMessage(dto.getMessage()+" "+ RandomStringUtils.random(10,true,true), dto.getId());
                redisProducer.sendMessage(dto.getType()+"",dto.getId()+"");
            }
        }
    }
}

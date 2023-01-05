package com.notificationresponse.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notificationresponse.controller.NotificationResponseController;
import com.notificationresponse.model.NotificationResponse;
import com.notificationresponse.model.dto.Message;
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
                dto.setChangeField(""+new Random().nextInt(100000));
                restConsumer.update(dto);
                redisProducer.sendMessage(dto.getType()+"",dto.getId()+"");
            }
            case password -> {
                dto.setChangeField(""+RandomStringUtils.random(15,true,true));
                restConsumer.update(dto);
                redisProducer.sendMessage(dto.getType()+"",dto.getId()+"");
            }
        }
    }
}

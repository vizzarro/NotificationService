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
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Message message = objectMapper.readValue(dto.getMessage(), Message.class);
                    dto.setChangeField(""+new Random().nextInt(10000));
                    restConsumer.update(dto);
                    redisProducer.sendMessage(dto.getType()+"",dto.getId()+"");
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            case password -> {
                //restConsumer.updateMessage(dto.getMessage()+" "+ RandomStringUtils.random(10,true,true), dto.getId());
                redisProducer.sendMessage(dto.getType()+"",dto.getId()+"");
            }
        }
    }
}

package com.notificationresponse.services;

import com.notificationresponse.model.dto.NotificationResponseDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class NotificationResponseParser {
    private final RedisProducer redisProducer;
    @Autowired
    public  NotificationResponseParser(RedisProducer redisProducer){
        this.redisProducer=redisProducer;
    }
    public void parseResponse(NotificationResponseDTO dto){
        switch (dto.getAction()){
            case no -> redisProducer.sendMessage(dto.getType()+"", dto.getId()+"");
            case verify -> {
                dto.setMessage(dto.getMessage()+" "+new Random().nextInt());
                redisProducer.sendMessage(dto.getType()+"",dto.getId()+"");
            }
            case password -> {
                dto.setMessage(dto.getMessage()+" "+ RandomStringUtils.random(10,true,true));
                redisProducer.sendMessage(dto.getType()+"",dto.getId()+"");
            }

        }

    }
}

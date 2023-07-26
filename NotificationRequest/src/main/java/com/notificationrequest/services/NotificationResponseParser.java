package com.notificationrequest.services;

import com.notificationrequest.model.dto.NotificationRequestDTO;
import com.notificationrequest.model.dto.State;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class NotificationResponseParser {
    private final RedisProducer redisProducer;
    private final RestConsumer restConsumer;


    @Autowired
    public NotificationResponseParser(RedisProducer redisProducer, RestConsumer restConsumer){
        this.redisProducer=redisProducer;
        this.restConsumer = restConsumer;
    }
    public void parseResponse(NotificationRequestDTO dto){
        //in base all'azione decide se modificare o no la stringa
        switch (dto.getAction()){
            case no -> {
                dto.setChangeField(" ");
                restConsumer.updateRequestState(dto.getId(), State.parsed.toString());
                restConsumer.update(dto);
                redisProducer.sendMessage(dto.getType()+"",dto.getId()+"");
            }
            case verify -> {
                dto.setChangeField(""+new Random().nextInt(100000));
                restConsumer.updateRequestState(dto.getId(), State.parsed.toString());
                restConsumer.update(dto);
                redisProducer.sendMessage(dto.getType()+"",dto.getId()+"");
            }
            case password -> {
                dto.setChangeField(""+RandomStringUtils.random(15,true,true));
                restConsumer.updateRequestState(dto.getId(), State.parsed.toString());
                restConsumer.update(dto);
                redisProducer.sendMessage(dto.getType()+"",dto.getId()+"");
            }
        }
    }
}

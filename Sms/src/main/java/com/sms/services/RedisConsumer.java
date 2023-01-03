package com.sms.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.model.dto.NotificationResponseDTO;
import com.sms.model.dto.SmsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.ArrayList;
import java.util.List;


public class RedisConsumer implements MessageListener {
    //Logger logger = (Logger) LoggerFactory.getLogger(RedisConsumer.class);
    public final List<String> messageConsumer = new ArrayList<String>();
    RestConsumer restConsumer;
    @Autowired
    public RedisConsumer(RestConsumer restConsumer){
        this.restConsumer = restConsumer;
    }
    @Override
    public void onMessage(Message message, byte[] pattern) {
        NotificationResponseDTO responseDTO = restConsumer.getResponse(Integer.parseInt(message.toString()));
        String dto = restConsumer.createSms(responseDTO.getMessage(), responseDTO.getId());
        try {
            SmsDTO smsDTO = new ObjectMapper().readValue(dto,SmsDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

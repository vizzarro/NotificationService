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
    SmsParser smsParser;
    @Autowired
    public RedisConsumer(RestConsumer restConsumer, SmsParser smsParser){

        this.restConsumer = restConsumer;
        this.smsParser=smsParser;
    }
    @Override
    public void onMessage(Message message, byte[] pattern) {
        NotificationResponseDTO responseDTO = restConsumer.getResponse(Integer.parseInt(message.toString()));
        try {
            com.sms.model.Message message1 = new ObjectMapper().readValue(responseDTO.getMessage(), com.sms.model.Message.class);
            message1.setText(message1.getText()+" "+responseDTO.getChangeField());
            String dto = restConsumer.createSms(message1.getText(), responseDTO.getId());
            try {
                SmsDTO smsDTO = new ObjectMapper().readValue(dto,SmsDTO.class);
                smsParser.parseSms(smsDTO, message1);

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}

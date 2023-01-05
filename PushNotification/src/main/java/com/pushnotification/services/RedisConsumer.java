package com.pushnotification.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pushnotification.model.dto.NotificationResponseDTO;
import com.pushnotification.model.dto.PushNotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.ArrayList;
import java.util.List;


public class RedisConsumer implements MessageListener {
    public final List<String> messageConsumer = new ArrayList<String>();
    RestConsumer restConsumer;
    @Autowired
    public RedisConsumer(RestConsumer restConsumer){
        this.restConsumer = restConsumer;
    }
    @Override
    public void onMessage(Message message, byte[] pattern) {
        NotificationResponseDTO responseDTO = restConsumer.getResponse(Integer.parseInt(message.toString()));
        try {
            com.pushnotification.model.Message message1 = new ObjectMapper().readValue(responseDTO.getMessage(), com.pushnotification.model.Message.class);
            message1.setText(message1.getText()+" "+responseDTO.getChangeField());
            String dto = restConsumer.createPushNotification(message1.getTopic(),message1.getTitle(),message1.getText(), responseDTO.getId());
            try {
                PushNotificationDTO pushDTO = new ObjectMapper().readValue(dto,PushNotificationDTO.class);
                //todo creazione push notification effettivo
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}

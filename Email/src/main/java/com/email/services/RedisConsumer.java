package com.email.services;

import com.email.model.dto.EmailDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.email.model.dto.NotificationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.ArrayList;
import java.util.List;


public class RedisConsumer implements MessageListener {
    public final List<String> messageConsumer = new ArrayList<String>();
    private final RestConsumer restConsumer;

    @Autowired
    public RedisConsumer(RestConsumer restConsumer){
        this.restConsumer = restConsumer;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

        NotificationResponseDTO responseDTO = restConsumer.getResponse(Integer.parseInt(message.toString()));
        //todo: dare l'id del canale dal nome tramite restTemplate
        String dto = restConsumer.createEmail("no reply",responseDTO.getMessage(), null,responseDTO.getId());
        try {
            EmailDTO emailDTO = new ObjectMapper().readValue(dto,EmailDTO.class);
            //todo creazione mail effettiva

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}

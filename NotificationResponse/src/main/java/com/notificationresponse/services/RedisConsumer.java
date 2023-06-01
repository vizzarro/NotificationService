package com.notificationresponse.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notificationresponse.model.dto.ChannelDTO;
import com.notificationresponse.model.dto.NotificationRequestDTO;
import com.notificationresponse.model.dto.NotificationResponseDTO;
import com.notificationresponse.model.dto.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class RedisConsumer implements MessageListener {
    public final List<String> messageConsumer = new ArrayList<String>();
    private final RestConsumer restConsumer;
    private final NotificationResponseParser responseParser;
    Logger logger = Logger.getLogger(RedisConsumer.class.getName());

    @Autowired
    public RedisConsumer(RestConsumer restConsumer, NotificationResponseParser responseParser) {
        this.restConsumer = restConsumer;
        this.responseParser = responseParser;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println(message.toString());//qui da mettere il log
        NotificationRequestDTO requestDTO = restConsumer.getRequest(Integer.parseInt(message.toString()));

        ChannelDTO channelDTO = restConsumer.getChannel(requestDTO.getType().toString());
        String dto = restConsumer.createNotificationResponse(requestDTO.getAction().toString(), requestDTO.getMessage(), State.created.toString(), requestDTO.getType().toString(), "", channelDTO.getId(), Integer.parseInt(requestDTO.getId().toString()));
        try {
            NotificationResponseDTO responseDTO = new ObjectMapper().readValue(dto, NotificationResponseDTO.class);
            responseParser.parseResponse(responseDTO);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}

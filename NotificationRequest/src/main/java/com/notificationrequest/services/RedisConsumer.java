package com.notificationrequest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.notificationrequest.model.dto.ChannelDTO;
import com.notificationrequest.model.dto.NotificationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class RedisConsumer implements MessageListener {
    public final List<String> messageConsumer = new ArrayList<String>();
    Logger logger = Logger.getLogger(RedisConsumer.class.getName());
    private final RestConsumer restConsumer;
    private final NotificationResponseParser responseParser;


    @Autowired
    public RedisConsumer(RestConsumer restConsumer, NotificationResponseParser responseParser){
        this.restConsumer = restConsumer;
        this.responseParser = responseParser;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println(new String(pattern, StandardCharsets.UTF_8));;//qui da mettere il log

        NotificationRequestDTO requestDTO = restConsumer.getRequest(Integer.parseInt(message.toString()));
        responseParser.parseResponse(requestDTO);


    }
}

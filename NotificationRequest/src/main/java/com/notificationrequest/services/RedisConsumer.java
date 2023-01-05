package com.notificationrequest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.ArrayList;
import java.util.List;


public class RedisConsumer implements MessageListener {
    public final List<String> messageConsumer = new ArrayList<String>();


    @Autowired
    public RedisConsumer(){

    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println(message.toString());//qui da mettere il log

    }
}

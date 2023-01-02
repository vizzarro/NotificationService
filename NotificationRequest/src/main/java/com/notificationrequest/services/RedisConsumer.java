package com.notificationrequest.services;

import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;



public class RedisConsumer implements MessageListener {
    //Logger logger = (Logger) LoggerFactory.getLogger(RedisConsumer.class);
    public final List<String> messageConsumer = new ArrayList<String>();
    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Message: "+ message.toString());

        messageConsumer.add(message.toString());
    }
}

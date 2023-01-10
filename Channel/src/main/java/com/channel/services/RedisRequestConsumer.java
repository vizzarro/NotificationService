package com.channel.services;

import com.channel.model.dto.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RedisRequestConsumer implements MessageListener {
    Logger logger = Logger.getLogger(RestConsumer.class.getName());
    RestConsumer restConsumer;


    @Autowired
    public RedisRequestConsumer(RestConsumer restConsumer){
        this.restConsumer = restConsumer;

    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

        logger.log(Level.INFO,"channel: notificationRequest , message:"+message.toString());
        restConsumer.updateRequestState(Integer.parseInt(message.toString()), State.sended.toString());


    }
}

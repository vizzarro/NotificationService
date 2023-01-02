package com.channel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;

import java.util.ArrayList;
import java.util.List;


public class RedisConsumer implements MessageListener {
    RestConsumer restConsumer;

    ChannelTopic topic;
    @Autowired
    public RedisConsumer(RestConsumer restConsumer, ChannelTopic topic){
        this.restConsumer = restConsumer;
        this.topic =topic;
    }
    public final List<String> messageConsumer = new ArrayList<String>();
    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Message: "+ message.toString());
        if(!(messageConsumer.contains(message.toString()))) {
            restConsumer.createChannel(topic.getTopic(),"info1");
        }
        messageConsumer.add(message.toString());
    }
}

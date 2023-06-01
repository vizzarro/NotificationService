package com.notificationresponse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisProducer {
    StringRedisTemplate template;

    @Autowired
    public RedisProducer(StringRedisTemplate template) {
        this.template = template;
    }

    public void sendMessage(String channel, String message) {
        template.convertAndSend(channel, message);
    }

}

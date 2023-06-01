package com.channel.services;

import com.channel.model.dto.NotificationRequestDTO;
import com.channel.model.dto.NotificationResponseDTO;
import com.channel.model.dto.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.logging.Level;
import java.util.logging.Logger;


public class RedisEmailConsumer implements MessageListener {
    Logger logger = Logger.getLogger(RestConsumer.class.getName());
    RestConsumer restConsumer;


    @Autowired
    public RedisEmailConsumer(RestConsumer restConsumer) {
        this.restConsumer = restConsumer;

    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        logger.log(Level.INFO, " channel: email , message:" + message.toString());
        NotificationResponseDTO responseDTO = restConsumer.getResponse(Integer.parseInt(message.toString()));
        logger.log(Level.INFO, "response id:" + responseDTO.getId() + "");
        NotificationRequestDTO requestDTO = restConsumer.getRequest(responseDTO.getRequest());
        logger.log(Level.INFO, "request id:" + requestDTO.getId() + "");
        restConsumer.updateRequestState(Integer.parseInt(requestDTO.getId().toString()), State.processed.toString());

    }
}

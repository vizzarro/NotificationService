package com.email.services;

import com.email.model.dto.EmailDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.email.model.dto.NotificationResponseDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.ArrayList;
import java.util.List;


public class RedisConsumer implements MessageListener {
    public final List<String> messageConsumer = new ArrayList<String>();
    private final RestConsumer restConsumer;
    private final EmailParser emailParser;

    @Autowired
    public RedisConsumer(RestConsumer restConsumer, EmailParser emailParser){

        this.restConsumer = restConsumer;
        this.emailParser = emailParser;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        NotificationResponseDTO responseDTO = restConsumer.getResponse(Integer.parseInt(message.toString()));
        try {
            com.email.model.Message message1 = new ObjectMapper().readValue(responseDTO.getMessage(), com.email.model.Message.class);
            message1.setText(message1.getText()+" "+responseDTO.getChangeField());
            String dto = restConsumer.createEmail(message1.getSubject(),message1.getText(), message1.getFilePath(),responseDTO.getId());
            try {
                EmailDTO emailDTO = new ObjectMapper().readValue(dto,EmailDTO.class);
                emailParser.parseEmail(emailDTO, message1);

            } catch (JsonProcessingException | MessagingException e) {
                throw new RuntimeException(e);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }



    }
}

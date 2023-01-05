package com.email.services;

import com.email.model.Message;
import com.email.model.dto.EmailDTO;
import com.email.model.dto.NotificationResponseDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailParser {
    private final RestConsumer restConsumer;
    @Autowired
    public EmailParser(RestConsumer restConsumer){
        this.restConsumer = restConsumer;
    }
    public void parseEmail(EmailDTO dto, NotificationResponseDTO notificationResponseDTO) {
        /*
        try {
            //Message message  = new ObjectMapper().readValue(notificationResponseDTO.getMessage(), Message.class);
            //dto.setText(message.getText()+notificationResponseDTO.getActionField());

        } catch (JsonProcessingException ex){
            //todo gestire l'eccezione
            System.out.println("A");
        }*/

    }
}

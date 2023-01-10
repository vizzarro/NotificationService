package com.pushnotification.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.pushnotification.model.dto.PushNotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class PushNotificationParser {
    FirebaseMessaging fcm;
    @Autowired
    public PushNotificationParser(FirebaseMessaging fcm){
        this.fcm = fcm;
    }

    public void parseNotification(PushNotificationDTO dto, com.pushnotification.model.Message message1) {

        if (dto.getTopic() != null){
            Message msg = Message.builder()
                    .setTopic(dto.getTopic())
                    .putData(dto.getTitle(), dto.getBody())
                    .build();
            try {
                fcm.send(msg);
            } catch (FirebaseMessagingException e) {
                throw new RuntimeException(e);
            }

        }else {
            Message message = Message.builder().setToken(message1.getToken()).putData(dto.getTitle(),dto.getBody()).build();
            try {
                fcm.send(message);
            } catch (FirebaseMessagingException e) {
                throw new RuntimeException(e);
            }

        }

    }
}

package com.sms.services;

import com.sms.model.dto.NotificationRequestDTO;
import com.sms.model.dto.NotificationResponseDTO;
import com.sms.model.dto.SmsDTO;
import com.sms.model.dto.State;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
import com.twilio.rest.lookups.v1.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SmsParser {
    Logger logger= Logger.getLogger(SmsParser.class.getName());
    private RestConsumer restConsumer;
    @Autowired
    public SmsParser(RestConsumer restConsumer){
        this.restConsumer=restConsumer;

    }


    public void parseSms(SmsDTO dto, com.sms.model.Message message1){
        //PhoneNumber receiver = PhoneNumber.fetcher(new com.twilio.type.PhoneNumber(message1.getReceiver())).setCountryCode("IT").fetch();
        Message message = Message.creator(
                        //receiver.getPhoneNumber(),
                        new com.twilio.type.PhoneNumber("whatsapp:"+message1.getReceiver()),
                        new com.twilio.type.PhoneNumber("whatsapp:"+message1.getSender()),
                        dto.getMessage())
                .create();

        NotificationRequestDTO responseDTO = restConsumer.getRequest(dto.getResponse());
        restConsumer.updateResponseState(responseDTO.getId(), State.complete.toString());
        System.out.println(responseDTO.getId());
        restConsumer.updateRequestState(responseDTO.getId(),State.complete.toString());

        logger.log(Level.INFO, message.toString());

    }
}

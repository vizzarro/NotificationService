package com.sms.services;

import com.sms.model.dto.SmsDTO;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
import com.twilio.rest.lookups.v1.PhoneNumber;

public class SmsParser {


    public void parseSms(SmsDTO dto, com.sms.model.Message message1){
        PhoneNumber receiver = PhoneNumber.fetcher(new com.twilio.type.PhoneNumber(message1.getReceiver())).setCountryCode("IT").fetch();
        Message message = Message.creator(
                        receiver.getPhoneNumber(),
                        new com.twilio.type.PhoneNumber(message1.getSender()),
                        dto.getMessage())
                .create();

        System.out.println(message);

    }
}

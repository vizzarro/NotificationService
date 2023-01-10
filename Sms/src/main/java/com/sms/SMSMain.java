package com.sms;

import com.twilio.Twilio;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SMSMain {
    public static final String ACCOUNT_SID = "AC5d8ef93d3df7d4966ba3d11926eaed67";
    public static final String AUTH_TOKEN = "669780a13dd40079461616622f11fd93";
    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        SpringApplication.run(SMSMain.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
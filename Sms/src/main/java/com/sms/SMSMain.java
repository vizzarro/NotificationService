package com.sms;

import com.twilio.Twilio;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SMSMain {
    public static final String ACCOUNT_SID = "";
    public static final String AUTH_TOKEN = "";
    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        SpringApplication.run(SMSMain.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
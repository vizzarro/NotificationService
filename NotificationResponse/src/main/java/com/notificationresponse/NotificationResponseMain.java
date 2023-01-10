package com.notificationresponse;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
public class NotificationResponseMain {
    public static void main(String[] args) {SpringApplication.run(NotificationResponseMain.class, args);}
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
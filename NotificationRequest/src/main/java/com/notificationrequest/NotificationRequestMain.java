package com.notificationrequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@SpringBootApplication
public class NotificationRequestMain {
    public static void main(String[] args) {
        SpringApplication.run(NotificationRequestMain.class, args);

    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd ");
        DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper();

        // Registro global do serializer/deserializer para datas sem horário
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer(localDateFormatter));
        simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(localDateFormatter));
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(localDateTimeFormatter));
        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(localDateTimeFormatter));

        mapper.registerModule(simpleModule);
        return mapper;
    }


}

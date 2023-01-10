package com.email.services;

import com.email.model.Message;
import com.email.model.dto.EmailDTO;
import com.email.model.dto.NotificationResponseDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailParser {
    private JavaMailSender javaMailSender;
    @Autowired
    public EmailParser(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }
    public void parseEmail(EmailDTO dto, Message message) throws MessagingException {

        if(dto.getFilePath()!= null){
            MimeMessage emailMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(emailMessage, true);
            helper.setFrom(message.getSender());
            helper.setTo(message.getReceiver());
            helper.setSubject(dto.getSubject());
            helper.setText(dto.getText());

            FileSystemResource file
                    = new FileSystemResource(new File(dto.getFilePath()));
            helper.addAttachment("File", file);
        } else {
            SimpleMailMessage emailMessage = new SimpleMailMessage();
            emailMessage.setFrom(message.getSender());
            emailMessage.setTo(message.getReceiver());
            emailMessage.setSubject(dto.getSubject());
            emailMessage.setText(dto.getText());
            javaMailSender.send(emailMessage);
        }





    }
}

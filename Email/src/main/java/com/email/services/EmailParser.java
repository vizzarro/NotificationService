package com.email.services;

import com.email.model.Message;
import com.email.model.dto.EmailDTO;
import com.email.model.dto.NotificationRequestDTO;
import com.email.model.dto.NotificationResponseDTO;
import com.email.model.dto.State;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailParser {
    private final JavaMailSender javaMailSender;
    private RestConsumer restConsumer;
    @Autowired
    public EmailParser(JavaMailSender javaMailSender, RestConsumer restConsumer){
        this.javaMailSender = javaMailSender;
        this.restConsumer = restConsumer;
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
            javaMailSender.send(emailMessage);
        } else {
            SimpleMailMessage emailMessage = new SimpleMailMessage();
            emailMessage.setFrom(message.getSender());
            emailMessage.setTo(message.getReceiver());
            emailMessage.setSubject(dto.getSubject());
            emailMessage.setText(dto.getText());
            javaMailSender.send(emailMessage);
            NotificationResponseDTO responseDTO = restConsumer.getResponse(dto.getResponse());
            restConsumer.updateResponseState(responseDTO.getId(), State.received.toString());
            restConsumer.updateRequestState(responseDTO.getRequest(),State.received.toString());
        }

    }
}

package com.email.services;

import com.email.model.Message;
import com.email.model.dto.EmailDTO;
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
    public EmailParser(JavaMailSender javaMailSender, RestConsumer restConsumer) {
        this.javaMailSender = javaMailSender;
        this.restConsumer = restConsumer;
    }

    public void parseEmail(EmailDTO dto, Message message) throws MessagingException {

        if (dto.getFilePath() != null) {
            MimeMessage emailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(emailMessage, true);

            helper.setFrom(message.getSender());
            helper.setSubject(dto.getSubject());
            helper.setText(dto.getText());

            if (message.getReceiver().getAddressCC() != null && message.getReceiver().getAddressBCC() != null && message.getReceiver().getAddress() != null) {
                String[] bcc = message.getReceiver().getAddressBCC().toArray(new String[0]);
                helper.setBcc(bcc);
                String[] cc = message.getReceiver().getAddressCC().toArray(new String[0]);
                helper.setCc(cc);
                String[] to = message.getReceiver().getAddress().toArray(new String[0]);
                helper.setTo(to);
            }
            if (message.getReceiver().getAddress() != null && message.getReceiver().getAddressCC() == null && message.getReceiver().getAddressBCC() == null) {
                String[] to = message.getReceiver().getAddress().toArray(new String[0]);
                helper.setTo(to);
            }
            if (message.getReceiver().getAddress() == null && message.getReceiver().getAddressCC() != null && message.getReceiver().getAddressBCC() != null) {
                String[] bcc = message.getReceiver().getAddressBCC().toArray(new String[0]);
                helper.setBcc(bcc);
                String[] cc = message.getReceiver().getAddressCC().toArray(new String[0]);
                helper.setCc(cc);
            }
            if (message.getReceiver().getAddress() != null && message.getReceiver().getAddressCC() != null && message.getReceiver().getAddressBCC() == null) {
                String[] cc = message.getReceiver().getAddressCC().toArray(new String[0]);
                helper.setCc(cc);
                String[] to = message.getReceiver().getAddress().toArray(new String[0]);
                helper.setTo(to);
            }
            if (message.getReceiver().getAddress() != null && message.getReceiver().getAddressCC() == null && message.getReceiver().getAddressBCC() != null) {
                String[] bcc = message.getReceiver().getAddressBCC().toArray(new String[0]);
                helper.setBcc(bcc);
                String[] to = message.getReceiver().getAddress().toArray(new String[0]);
                helper.setTo(to);
            }
            if (message.getReceiver().getAddress() == null && message.getReceiver().getAddressCC() == null && message.getReceiver().getAddressBCC() != null) {
                String[] bcc = message.getReceiver().getAddressBCC().toArray(new String[0]);
                helper.setBcc(bcc);
            }
            if (message.getReceiver().getAddress() == null && message.getReceiver().getAddressCC() != null && message.getReceiver().getAddressBCC() == null) {
                String[] cc = message.getReceiver().getAddressCC().toArray(new String[0]);
                helper.setCc(cc);
            }
            FileSystemResource file
                    = new FileSystemResource(new File(dto.getFilePath()));
            helper.addAttachment("File", file);

            updateState(dto);
            javaMailSender.send(emailMessage);

        } else {
            SimpleMailMessage emailMessage = new SimpleMailMessage();

            emailMessage.setFrom(message.getSender());
            emailMessage.setSubject(dto.getSubject());
            emailMessage.setText(dto.getText());

            if (message.getReceiver().getAddressCC() != null && message.getReceiver().getAddressBCC() != null && message.getReceiver().getAddress() != null) {
                String[] bcc = message.getReceiver().getAddressBCC().toArray(new String[0]);
                emailMessage.setBcc(bcc);
                String[] cc = message.getReceiver().getAddressCC().toArray(new String[0]);
                emailMessage.setCc(cc);
                String[] to = message.getReceiver().getAddress().toArray(new String[0]);
                emailMessage.setTo(to);
            }
            if (message.getReceiver().getAddress() != null && message.getReceiver().getAddressCC() == null && message.getReceiver().getAddressBCC() == null) {
                String[] to = message.getReceiver().getAddress().toArray(new String[0]);
                emailMessage.setTo(to);
            }
            if (message.getReceiver().getAddress() == null && message.getReceiver().getAddressCC() != null && message.getReceiver().getAddressBCC() != null) {
                String[] bcc = message.getReceiver().getAddressBCC().toArray(new String[0]);
                emailMessage.setBcc(bcc);
                String[] cc = message.getReceiver().getAddressCC().toArray(new String[0]);
                emailMessage.setCc(cc);
            }
            if (message.getReceiver().getAddress() != null && message.getReceiver().getAddressCC() != null && message.getReceiver().getAddressBCC() == null) {
                String[] cc = message.getReceiver().getAddressCC().toArray(new String[0]);
                emailMessage.setCc(cc);
                String[] to = message.getReceiver().getAddress().toArray(new String[0]);
                emailMessage.setTo(to);
            }
            if (message.getReceiver().getAddress() != null && message.getReceiver().getAddressCC() == null && message.getReceiver().getAddressBCC() != null) {
                String[] bcc = message.getReceiver().getAddressBCC().toArray(new String[0]);
                emailMessage.setBcc(bcc);
                String[] to = message.getReceiver().getAddress().toArray(new String[0]);
                emailMessage.setTo(to);
            }
            if (message.getReceiver().getAddress() == null && message.getReceiver().getAddressCC() == null && message.getReceiver().getAddressBCC() != null) {
                String[] bcc = message.getReceiver().getAddressBCC().toArray(new String[0]);
                emailMessage.setBcc(bcc);
            }
            if (message.getReceiver().getAddress() == null && message.getReceiver().getAddressCC() != null && message.getReceiver().getAddressBCC() == null) {
                String[] cc = message.getReceiver().getAddressCC().toArray(new String[0]);
                emailMessage.setCc(cc);
            }
            updateState(dto);
            javaMailSender.send(emailMessage);

        }

    }

    public void updateState(EmailDTO dto) {
        NotificationResponseDTO responseDTO = restConsumer.getResponse(dto.getResponse());
        restConsumer.updateResponseState(responseDTO.getId(), State.complete.toString());
        System.out.println(responseDTO.getRequest());
        restConsumer.updateRequestState(responseDTO.getRequest(), State.complete.toString());

    }
}

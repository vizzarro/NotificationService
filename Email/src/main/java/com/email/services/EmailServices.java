package com.email.services;

import com.email.model.Email;
import com.email.model.dto.EmailDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EmailServices {
    private EmailRepository emailRepository;
    private ModelMapper modelMapper;

    @Autowired
    public EmailServices(EmailRepository emailRepository, ModelMapper modelMapper){
        this.emailRepository  = emailRepository;
        this.modelMapper = modelMapper;
    }
    public EmailDTO save(EmailDTO c){
        Email email= modelMapper.map(c,Email.class);
        emailRepository.save(email);
        return  modelMapper.map(email, EmailDTO.class);
    }
    public List<EmailDTO> findAll(){
        return emailRepository.findAll().stream().map(e->modelMapper.map(e,EmailDTO.class)).collect(Collectors.toList());
    }
    public EmailDTO findById(int id){
        Email channel = emailRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return  modelMapper.map(channel, EmailDTO.class);
    }
    public void delete(int id){
        emailRepository.deleteById(id);
    }
    public long count(){
        return emailRepository.count();
    }


}

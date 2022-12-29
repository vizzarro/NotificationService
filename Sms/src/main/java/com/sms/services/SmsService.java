package com.sms.services;

import com.sms.model.Sms;
import com.sms.model.dto.SmsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmsService {
    private SmsRepository smsRepository;
    private ModelMapper modelMapper;
    @Autowired
    public SmsService(SmsRepository smsRepository, ModelMapper modelMapper){
        this.smsRepository =smsRepository;
        this.modelMapper = modelMapper;
    }
    public SmsDTO save(SmsDTO s){
        Sms sms  = modelMapper.map(s, Sms.class);
        smsRepository.save(sms);
        return modelMapper.map(sms, SmsDTO.class);
    }
    public SmsDTO findById(int id) {
        Sms sms = smsRepository.findById(id).orElseThrow();
        return modelMapper.map(sms, SmsDTO.class);
    }

    public void delete(int id){smsRepository.deleteById(id);}

    public long count() {return  smsRepository.count();}

    public List<SmsDTO> findAll(){
        return smsRepository.findAll().stream().map(e->modelMapper.map(e,SmsDTO.class)).collect(Collectors.toList());
    }


}

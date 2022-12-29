package com.pushnotification.services;

import com.pushnotification.model.PushNotification;
import com.pushnotification.model.dto.PushNotificationDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PushNotificationServices {
    private PushNotificationRepository pushNotificationRepository;
    private ModelMapper modelMapper;
    @Autowired
    public PushNotificationServices(PushNotificationRepository pushNotificationRepository, ModelMapper modelMapper){
        this.pushNotificationRepository = pushNotificationRepository;
        this.modelMapper = modelMapper;
    }
    public PushNotificationDTO save(PushNotificationDTO p){
        PushNotification push = modelMapper.map(p, PushNotification.class);
        pushNotificationRepository.save(push);
        return modelMapper.map(push, PushNotificationDTO.class);
    }
    public PushNotificationDTO findById(int id){
        PushNotification pushNotification = pushNotificationRepository.findById(id).orElseThrow();
        return modelMapper.map(pushNotification, PushNotificationDTO.class);
    }
    public void delete(int id){pushNotificationRepository.deleteById(id);}
    public long count() {return  pushNotificationRepository.count();}
    public List<PushNotificationDTO> findAll(){
        return  pushNotificationRepository.findAll().stream().map(e->modelMapper.map(e, PushNotificationDTO.class)).collect(Collectors.toList());
    }


}

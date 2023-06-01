package com.notificationresponse.services;

import com.notificationresponse.model.NotificationResponse;
import com.notificationresponse.model.dto.NotificationResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationResponseServices {
    private final NotificationResponseRepository notificationResponseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public NotificationResponseServices(NotificationResponseRepository notificationResponseRepository, ModelMapper modelMapper) {
        this.notificationResponseRepository = notificationResponseRepository;
        this.modelMapper = modelMapper;

    }

    public NotificationResponseDTO save(NotificationResponseDTO n) {
        NotificationResponse response = modelMapper.map(n, NotificationResponse.class);
        notificationResponseRepository.save(response);
        return modelMapper.map(response, NotificationResponseDTO.class);
    }

    public NotificationResponseDTO findById(int id) {
        NotificationResponse response = notificationResponseRepository.findById(id).orElseThrow();
        return modelMapper.map(response, NotificationResponseDTO.class);
    }

    public void delete(int id) {
        notificationResponseRepository.deleteById(id);
    }

    public long count() {
        return notificationResponseRepository.count();
    }

    public List<NotificationResponseDTO> findAll() {
        return notificationResponseRepository.findAll().stream().map(e -> modelMapper.map(e, NotificationResponseDTO.class)).collect(Collectors.toList());
    }
}

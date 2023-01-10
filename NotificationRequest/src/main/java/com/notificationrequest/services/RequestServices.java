package com.notificationrequest.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notificationrequest.model.NotificationRequest;
import com.notificationrequest.model.dto.ChannelDTO;
import com.notificationrequest.model.dto.NotificationRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RequestServices {
    private final RequestRepository requestRepository;
    private final ModelMapper modelMapper;
    private final RedisTemplate template;
    private RestConsumer restConsumer;
    private final ChannelTopic topic;
    @Autowired
   public RequestServices(RequestRepository requestRepository,ModelMapper modelMapper, RedisTemplate template, ChannelTopic topic, RestConsumer restConsumer){
        this.requestRepository = requestRepository;
        this.modelMapper  = modelMapper;
        this.template = template;
        this.topic = topic;
        this.restConsumer = restConsumer;
    }

    public NotificationRequestDTO save(NotificationRequestDTO r){
        NotificationRequest request = modelMapper.map(r, NotificationRequest.class);
        request.setDate(LocalDate.now());
        request.setTime(LocalTime.now());
        requestRepository.save(request);
        //qui ci va un try catch
        template.convertAndSend(topic.getTopic(), request.getId()+"");
        return modelMapper.map(request, NotificationRequestDTO.class);
    }
    public NotificationRequestDTO update(NotificationRequestDTO r){
        NotificationRequest request = modelMapper.map(r, NotificationRequest.class);
        request.setDate(LocalDate.now());
        request.setTime(LocalTime.now());
        requestRepository.save(request);
        return modelMapper.map(request, NotificationRequestDTO.class);
    }

    public NotificationRequestDTO findById(int id){
        NotificationRequest request = requestRepository.findById(id).orElseThrow(/*NoSuchElementException::new*/);
        return modelMapper.map(request, NotificationRequestDTO.class);
    }
    public void delete(int id) {requestRepository.deleteById(id);}
    public long count(){return requestRepository.count();}
    public List<NotificationRequestDTO> findAll(){
        Function<NotificationRequest, NotificationRequestDTO> fMap = e->modelMapper.map(e,NotificationRequestDTO.class);
        return requestRepository.findAll().stream().map(fMap).collect(Collectors.toList());
    }


}

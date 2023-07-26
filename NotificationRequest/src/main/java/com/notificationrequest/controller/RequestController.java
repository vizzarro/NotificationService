package com.notificationrequest.controller;

import com.notificationrequest.model.dto.*;
import com.notificationrequest.services.RequestServices;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/notificationrequest")
public class RequestController {

    RequestServices requestServices;

   @Autowired
    public RequestController(RequestServices requestServices){
        this.requestServices=requestServices;

    }

    @PostMapping
    public NotificationRequestDTO newRequest(@Valid @RequestBody NotificationRequestDTO r){
       return requestServices.save(r);
    }
    @GetMapping
    public NotificationRequestResponse getAllNotifications(){
        NotificationRequestResponse response = new NotificationRequestResponse();
        response.setData(requestServices.findAll());
        return response;
    }

    @GetMapping(path = "{id}")
    public NotificationRequestDTO getNotification(@PathVariable(value = "id") int id){
        return requestServices.findById(id);
    }
    @DeleteMapping(path = "{id}")
    public  void deleteNotification(@PathVariable(value = "id") int id) {
         requestServices.delete(id);
    }

    @PostMapping(path = "{id}") //qui d una variante è il patch(per oggetti complessi) dto
    public NotificationRequestDTO update(@PathVariable(value = "id") int id,@RequestBody NotificationRequestDTO r){
       r.setId(id);
       return requestServices.update(r);
    }
    @PostMapping(path = "{id}/{state}")
    public void updateState(@PathVariable(value = "id") int id, @PathVariable(value = "state") String state){
        NotificationRequestDTO dto = requestServices.findById(id);
        dto.setState(State.valueOf(state));
        requestServices.update(dto);
    }
    @PatchMapping(path = "{id}/{priority}")
    public  void updatePriority(@PathVariable(value = "id") int id, @PathVariable(value = "priority") String priority){
        NotificationRequestDTO dto = requestServices.findById(id);
        dto.setPriority(Priority.valueOf(priority));
        requestServices.update(dto);
    }
    @GetMapping(path = "count")
    public  long countRequests(){
        return requestServices.count();
    }



}

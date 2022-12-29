package com.notificationresponse.controller;

import com.notificationresponse.model.NotificationResponse;
import com.notificationresponse.model.dto.NotificationResponseDTO;
import com.notificationresponse.model.dto.State;
import com.notificationresponse.services.NotificationResponseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/notificationresponse")
public class NotificationResponseController {

    NotificationResponseServices notificationResponseServices;
    @Autowired
    public NotificationResponseController(NotificationResponseServices notificationResponseServices){
        this.notificationResponseServices = notificationResponseServices;
    }
    @PostMapping
    public NotificationResponseDTO newNotificationResponse(@RequestBody  NotificationResponseDTO n){return notificationResponseServices.save(n);}
    @GetMapping
    public List<NotificationResponseDTO> getAllResponse(){return notificationResponseServices.findAll();}
    @GetMapping(path = "{id}")
    public NotificationResponseDTO getResponse(@PathVariable(value = "id") int id){return notificationResponseServices.findById(id);}
    @DeleteMapping(path = "{id}")
    public void deleteResponse(@PathVariable(value = "id") int id) {notificationResponseServices.delete(id);}
    @PutMapping(path = "{id}")
    public NotificationResponseDTO update(@PathVariable(value = "id")  int id,@RequestBody NotificationResponseDTO n){
        n.setId(id);
        return notificationResponseServices.save(n);
    }
    @PatchMapping(path = "{id}/{action}")
    public void updateAction(@PathVariable(value = "id") int id, @PathVariable(value = "action")String action){
        NotificationResponseDTO response  = notificationResponseServices.findById(id);
        response.setAction(action);
        notificationResponseServices.save(response);
    }
    @PatchMapping(path = "{id}/{state}")
    public void updateState(@PathVariable(value = "id") int id, @PathVariable(value = "state")String state){
        NotificationResponseDTO response  = notificationResponseServices.findById(id);
        response.setState(State.valueOf(state));
        notificationResponseServices.save(response);
    }
    @PatchMapping(path = "{id}/{channel}")
    public void updateChannel(@PathVariable(value = "id") int id, @PathVariable(value = "channel") int channel){
        NotificationResponseDTO response  = notificationResponseServices.findById(id);
        response.setChannel(channel);
        notificationResponseServices.save(response);

    }

    @GetMapping(path = "count")
    public @ResponseBody long countResponse(){
        return notificationResponseServices.count();
    }
}

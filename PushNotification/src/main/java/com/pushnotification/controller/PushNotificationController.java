package com.pushnotification.controller;

import com.pushnotification.model.PushNotification;
import com.pushnotification.model.dto.PushNotificationDTO;
import com.pushnotification.services.PushNotificationRepository;
import com.pushnotification.services.PushNotificationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/pushnotification")
public class PushNotificationController {

   PushNotificationServices pushNotificationServices;

   @Autowired
   public PushNotificationController(PushNotificationServices pushNotificationServices){
       this.pushNotificationServices = pushNotificationServices;
   }
    @PostMapping
    public PushNotificationDTO newPushNotification(@RequestBody PushNotificationDTO p){return pushNotificationServices.save(p); }
    @GetMapping
    public List<PushNotificationDTO> getAllPushNotification(){return pushNotificationServices.findAll();}
    @GetMapping(path = "{id}")
    public PushNotificationDTO getPushNotification(@PathVariable(value = "id") int id){return pushNotificationServices.findById(id);}
    @DeleteMapping(path = "{id}")
    public void deletePushNotification(@PathVariable(value = "id") int id) {pushNotificationServices.delete(id);}
    @PutMapping(path = "{id}")
    public  PushNotificationDTO update(@PathVariable(value = "id") int id, @RequestBody PushNotificationDTO p){
      p.setId(id);
      return pushNotificationServices.save(p);
    }
    @PatchMapping(path = "{id}/{topic}")
    public void updateTopic(@PathVariable(value = "id") int id,@PathVariable(value = "topic") String topic){
        PushNotificationDTO push  = pushNotificationServices.findById(id);
        push.setTopic(topic);
        pushNotificationServices.save(push);
    }
    @PatchMapping(path = "{id}/{title}")
    public void updateTitle(@PathVariable(value = "id") int id,@PathVariable(value = "title") String title){
        PushNotificationDTO push  = pushNotificationServices.findById(id);
        push.setTitle(title);
        pushNotificationServices.save(push);
    }

    @GetMapping(path = "count")
    public long countPushNotification(){
        return pushNotificationServices.count();
    }
}

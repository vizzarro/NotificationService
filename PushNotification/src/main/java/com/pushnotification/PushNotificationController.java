package com.pushnotification;

import com.pushnotification.model.PushNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/pushnotification")
public class PushNotificationController {
   @Autowired
    PushNotificationRepository pushNotificationRepository;
    @PostMapping(path = "new")
    public @ResponseBody String newPushNotification(@RequestParam String topic,@RequestParam String title,@RequestParam String body, @RequestParam int response){
        PushNotification push = new PushNotification(topic,title,body, response);
        pushNotificationRepository.save(push);
        return "Saved";
    }
    @GetMapping(path = "/allpush")
    public @ResponseBody Iterable<PushNotification> getAllPushNotification(){
        return pushNotificationRepository.findAll();
    }
    @GetMapping(path = "/sms")
    public @ResponseBody Optional<PushNotification> getPushNotification(@RequestParam int id){
        return pushNotificationRepository.findById(id);
    }
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteSms(@RequestParam int id) {
        pushNotificationRepository.deleteById(id);
        return "Deleted";
    }
    @PostMapping(path = "update")
    public @ResponseBody String update(@RequestParam int id,@RequestParam String topic,@RequestParam String title,@RequestParam String body, @RequestParam int response){
        PushNotification push  = pushNotificationRepository.findById(id).get();
        push.setTopic(topic);
        push.setTitle(title);
        push.setBody(body);
        push.setResponse(response);
        pushNotificationRepository.save(push);
        return "Updated";
    }
    @PostMapping(path = "updatetopic")
    public @ResponseBody String updateTopic(@RequestParam int id,@RequestParam String topic){
        PushNotification push  = pushNotificationRepository.findById(id).get();
        push.setTopic(topic);
        pushNotificationRepository.save(push);
        return "Updated";
    }
    @PostMapping(path = "updatetitle")
    public @ResponseBody String updateTitle(@RequestParam int id,@RequestParam String title){
        PushNotification push  = pushNotificationRepository.findById(id).get();
        push.setTitle(title);
        pushNotificationRepository.save(push);
        return "Updated";
    }
    @PostMapping(path = "updatebody")
    public @ResponseBody String updateBody(@RequestParam int id,@RequestParam String body){
        PushNotification push  = pushNotificationRepository.findById(id).get();
        push.setBody(body);
        pushNotificationRepository.save(push);
        return "Updated";
    }
    @PostMapping(path = "updateresponse")
    public @ResponseBody String updateResponse(@RequestParam int id, @RequestParam int response){
        PushNotification push  = pushNotificationRepository.findById(id).get();
        push.setResponse(response);
        pushNotificationRepository.save(push);
        return "Updated";
    }
    @GetMapping(path = "exist")
    public @ResponseBody boolean isPushNotification(@RequestParam int id){
        return pushNotificationRepository.existsById(id);
    }
    @GetMapping(path = "size")
    public @ResponseBody long sizePushNotification(){
        return pushNotificationRepository.count();
    }
}

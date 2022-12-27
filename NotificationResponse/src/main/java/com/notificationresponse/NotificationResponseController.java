package com.notificationresponse;

import com.notificationresponse.model.NotificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/notificationresponse")
public class NotificationResponseController {
    @Autowired
    NotificationResponseRepository notificationResponseRepository;
    @PostMapping(path = "new")
    public @ResponseBody String newNotificationResponse(@RequestParam String action, @RequestParam String message, @RequestParam String state, @RequestParam int channel, @RequestParam int request){
        NotificationResponse response = new NotificationResponse(action,message,state,channel,request);
        notificationResponseRepository.save(response);
        return "Saved";
    }
    @GetMapping(path = "/allresponse")
    public @ResponseBody Iterable<NotificationResponse> getAllResponse(){
        return notificationResponseRepository.findAll();
    }
    @GetMapping(path = "/response")
    public @ResponseBody Optional<NotificationResponse> getResponse(@RequestParam int id){
        return notificationResponseRepository.findById(id);
    }
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteResponse(@RequestParam int id) {
        notificationResponseRepository.deleteById(id);
        return "Deleted";
    }
    @PostMapping(path = "update")
    public @ResponseBody String update(@RequestParam int id,@RequestParam String action, @RequestParam String message, @RequestParam String state, @RequestParam int channel, @RequestParam int request){
        NotificationResponse response  = notificationResponseRepository.findById(id).get();
        response.setAction(action);
        response.setMessage(message);
        response.setState(state);
        response.setChannel(channel);
        response.setRequest(request);
        notificationResponseRepository.save(response);
        return "Updated";
    }
    @PostMapping(path = "updateaction")
    public @ResponseBody String updateAction(@RequestParam int id,@RequestParam String action){
        NotificationResponse response  = notificationResponseRepository.findById(id).get();
        response.setAction(action);
        notificationResponseRepository.save(response);
        return "Updated";
    }
    @PostMapping(path = "updatemessage")
    public @ResponseBody String updateMessage(@RequestParam int id, @RequestParam String message){
        NotificationResponse response  = notificationResponseRepository.findById(id).get();
        response.setMessage(message);
        notificationResponseRepository.save(response);
        return "Updated";
    }
    @PostMapping(path = "updatestate")
    public @ResponseBody String updateState(@RequestParam int id, @RequestParam String state){
        NotificationResponse response  = notificationResponseRepository.findById(id).get();
        response.setState(state);
        notificationResponseRepository.save(response);
        return "Updated";
    }
    @PostMapping(path = "updatechannel")
    public @ResponseBody String updateChannel(@RequestParam int id, @RequestParam int channel){
        NotificationResponse response  = notificationResponseRepository.findById(id).get();
        response.setChannel(channel);
        notificationResponseRepository.save(response);
        return "Updated";
    }
    @PostMapping(path = "updaterequest")
    public @ResponseBody String updateRequest(@RequestParam int id, @RequestParam int request){
        NotificationResponse response  = notificationResponseRepository.findById(id).get();
        response.setRequest(request);
        notificationResponseRepository.save(response);
        return "Updated";
    }
    @GetMapping(path = "exist")
    public @ResponseBody boolean isResponse(@RequestParam int id){
        return notificationResponseRepository.existsById(id);
    }
    @GetMapping(path = "size")
    public @ResponseBody long sizeResponse(){
        return notificationResponseRepository.count();
    }
}

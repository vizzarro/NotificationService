package com.notificationrequest;

import com.notificationrequest.model.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping(path = "/notificationrequest")
public class RequestController {
    @Autowired
    RequestRepository requestRepository;
    @PostMapping(path = "/new")
    public @ResponseBody String newRequest(@RequestParam String state, @RequestParam String message, @RequestParam String type, @RequestParam String priority, @RequestParam boolean multicast){
        NotificationRequest request = new NotificationRequest(state, message, type, priority, multicast);
        requestRepository.save(request);
        return "Saved";
    }
    @GetMapping(path = "/requests")
    public @ResponseBody Iterable<NotificationRequest> getAllNotifications(){
        return requestRepository.findAll();
    }

    @GetMapping(path = "/request")
    public @ResponseBody Optional<NotificationRequest> getNotification(@RequestParam int id){
        return requestRepository.findById(id);
    }
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteNotification(@RequestParam int id) {
         requestRepository.deleteById(id);
         return "Deleted";
    }

    @PostMapping(path = "update")
    public @ResponseBody String update(@RequestParam int id,@RequestParam String state, @RequestParam String message, @RequestParam String type, @RequestParam String priority, @RequestParam boolean multicast){
        NotificationRequest request  = requestRepository.findById(id).get();
        request.setState(state);
        request.setMessage(message);
        request.setType(type);
        request.setPriority(priority);
        request.setMulticast(multicast);
        requestRepository.save(request);
        return "Updated";
    }
    @PostMapping(path = "updatestate")
    public @ResponseBody String updateState(@RequestParam int id, @RequestParam String state){
        NotificationRequest request  = requestRepository.findById(id).get();
        request.setState(state);
        requestRepository.save(request);
        return "Updated";
    }
    @PostMapping(path = "updatemessage")
    public @ResponseBody String updateMessage(@RequestParam int id, @RequestParam String message){
        NotificationRequest request  = requestRepository.findById(id).get();
        request.setMessage(message);
        requestRepository.save(request);
        return "Updated";
    }
    @PostMapping(path = "updatetype")
    public @ResponseBody String updateType(@RequestParam int id, @RequestParam String type){
        NotificationRequest request  = requestRepository.findById(id).get();
        request.setType(type);
        requestRepository.save(request);
        return "Updated";
    }

    @PostMapping(path = "updatepriority")
    public @ResponseBody String updatePriority(@RequestParam int id, @RequestParam String priority){
        NotificationRequest request  = requestRepository.findById(id).get();
        request.setPriority(priority);
        requestRepository.save(request);
       return "Updated";
    }

    @PostMapping(path = "updatemulticast")
    public @ResponseBody String updateMulticast(@RequestParam int id, @RequestParam boolean multicast){
        NotificationRequest request  = requestRepository.findById(id).get();
        request.setMulticast(multicast);
        requestRepository.save(request);
        return "Updated";
    }

    @GetMapping(path = "exist")
    public @ResponseBody boolean isRequest(@RequestParam int id){
       return requestRepository.existsById(id);
    }

    @GetMapping(path = "size")
    public @ResponseBody long sizeRequests(){
        return requestRepository.count();
    }


}

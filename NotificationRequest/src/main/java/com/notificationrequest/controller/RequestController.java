package com.notificationrequest.controller;

import com.notificationrequest.model.dto.NotificationRequestDTO;
import com.notificationrequest.model.dto.NotificationRequestResponse;
import com.notificationrequest.model.dto.Priority;
import com.notificationrequest.model.dto.State;
import com.notificationrequest.services.RequestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/notificationrequest")
public class RequestController {

    RequestServices requestServices;
   @Autowired
    public RequestController(RequestServices requestServices){
        this.requestServices  =requestServices;
    }
    @PostMapping
    public NotificationRequestDTO newRequest(@RequestBody NotificationRequestDTO r){
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

    @PutMapping(path = "{id}") //qui d una variante è il patch(per oggetti complessi) dto
    public NotificationRequestDTO update(@PathVariable(value = "id") int id,@RequestBody NotificationRequestDTO r){
        return requestServices.save(r);
    }
    @PatchMapping(path = "{id}/{state}")
    public void updateState(@PathVariable(value = "id") int id, @PathVariable(value = "state") String state){
        NotificationRequestDTO dto = requestServices.findById(id);
        dto.setState(State.valueOf(state));
        requestServices.save(dto);
    }
    @PostMapping(path = "{id}/{priority}")
    public  void updatePriority(@PathVariable(value = "id") int id, @PathVariable(value = "priority") String priority){
        NotificationRequestDTO dto = requestServices.findById(id);
        dto.setPriority(Priority.valueOf(priority));
        requestServices.save(dto);
    }
    @GetMapping(path = "count")
    public  long countRequests(){
        return requestServices.count();
    }


}

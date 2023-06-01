package com.notificationrequest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.notificationrequest.model.dto.*;
import com.notificationrequest.services.RequestServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/notificationrequest")
public class RequestController {

    RequestServices requestServices;

    @Autowired
    public RequestController(RequestServices requestServices) {
        this.requestServices = requestServices;

    }

    @PostMapping
    public EmailSenderRequestDTO newRequest(@Valid @RequestBody EmailSenderRequestDTO r) throws JsonProcessingException {
        return requestServices.save(r);
    }

    @GetMapping
    public NotificationRequestResponse getAllNotifications() {
        NotificationRequestResponse response = new NotificationRequestResponse();
        response.setData(requestServices.findAll());
        return response;
    }

    @GetMapping(path = "{id}")
    public NotificationRequestDTO getNotification(@PathVariable(value = "id") int id) {
        return requestServices.findById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteNotification(@PathVariable(value = "id") int id) {
        requestServices.delete(id);
    }

    @PutMapping(path = "{id}") //qui d una variante è il patch(per oggetti complessi) dto
    public NotificationRequestDTO update(@PathVariable(value = "id") long id, @RequestBody NotificationRequestDTO r) throws JsonProcessingException {
        r.setId(id);
        return requestServices.update(r);
    }

    @PostMapping(path = "{id}/{state}")
    public void updateState(@PathVariable(value = "id") int id, @PathVariable(value = "state") String state) throws JsonProcessingException {
        NotificationRequestDTO dto = requestServices.findById(id);
        dto.setState(State.valueOf(state));
        requestServices.update(dto);
    }

    @PatchMapping(path = "{id}/{priority}")
    public void updatePriority(@PathVariable(value = "id") int id, @PathVariable(value = "priority") String priority) throws JsonProcessingException {
        NotificationRequestDTO dto = requestServices.findById(id);
        dto.setPriority(Priority.valueOf(priority));
        requestServices.update(dto);
    }

    @GetMapping(path = "count")
    public long countRequests() {
        return requestServices.count();
    }


}

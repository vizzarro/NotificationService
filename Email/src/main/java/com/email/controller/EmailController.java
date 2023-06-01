package com.email.controller;

import com.email.model.dto.EmailDTO;
import com.email.services.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/email")
public class EmailController {
    EmailServices emailServices;

    @Autowired
    public EmailController(EmailServices emailServices) {
        this.emailServices = emailServices;
    }

    @PostMapping
    public EmailDTO newEmail(@RequestBody EmailDTO e) {
        return emailServices.save(e);
    }

    @GetMapping
    public List<EmailDTO> getAllEmail() {
        return emailServices.findAll();
    }

    @GetMapping(path = "{id}")
    public EmailDTO getEmail(@PathVariable(value = "id") int id) {
        return emailServices.findById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteEmail(@RequestParam int id) {
        emailServices.delete(id);
    }

    @PutMapping(path = "{id}")
    public EmailDTO update(@PathVariable(value = "id") int id, @RequestBody EmailDTO e) {
        e.setId(id);
        return emailServices.save(e);
    }

    @PatchMapping(path = "{id}/{subject}")
    public void updateSubject(@PathVariable(value = "id") int id, @PathVariable(value = "subject") String subject) {
        EmailDTO email = emailServices.findById(id);
        email.setSubject(subject);
        emailServices.save(email);
    }

    @PatchMapping(path = "{id}/{text}")
    public void updateText(@PathVariable(value = "id") int id, @PathVariable(value = "text") String text) {
        EmailDTO email = emailServices.findById(id);
        email.setText(text);
        emailServices.save(email);
    }

    @GetMapping(path = "size")
    public @ResponseBody long sizeEmail() {
        return emailServices.count();
    }
}

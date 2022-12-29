package com.sms.controller;

import com.sms.model.Sms;
import com.sms.model.dto.SmsDTO;
import com.sms.services.SmsRepository;
import com.sms.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/sms")
public class SmsController {

    SmsService smsService;
    @Autowired
    public SmsController(SmsService smsService){this.smsService = smsService;}
    @PostMapping
    public SmsDTO newSms(@RequestBody SmsDTO s){return smsService.save(s);}
    @GetMapping
    public List<SmsDTO> getAllSms(){
        return smsService.findAll();
    }
    @GetMapping(path = "{id}")
    public SmsDTO getSms(@PathVariable(value = "id") int id){
        return smsService.findById(id);
    }
    @DeleteMapping(path = "{id}")
    public void deleteSms(@PathVariable(value = "id") int id) {
        smsService.delete(id);
    }
    @PutMapping(path = "{id}")
    public SmsDTO update(@PathVariable(value = "id") int id,@RequestBody SmsDTO s){
        s.setId(id);
        return smsService.save(s);
    }
    @PatchMapping(path = "{id}/{message}")
    public void updateMessage(@PathVariable(value = "id") int id,@PathVariable(value = "message") String message){
        SmsDTO sms  = smsService.findById(id);
        sms.setMessage(message);
        smsService.save(sms);
    }

    @GetMapping(path = "count")
    public long countSms(){
        return smsService.count();
    }
}

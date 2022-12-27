package com.sms;

import com.sms.model.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/sms")
public class SmsController {
    @Autowired
    SmsRepository smsRepository;
    @PostMapping(path = "new")
    public @ResponseBody String newSms(@RequestParam String message, @RequestParam int response){
        Sms sms = new Sms(message, response);
        smsRepository.save(sms);
        return "Saved";
    }
    @GetMapping(path = "/allsms")
    public @ResponseBody Iterable<Sms> getAllSms(){
        return smsRepository.findAll();
    }
    @GetMapping(path = "/sms")
    public @ResponseBody Optional<Sms> getSms(@RequestParam int id){
        return smsRepository.findById(id);
    }
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteSms(@RequestParam int id) {
        smsRepository.deleteById(id);
        return "Deleted";
    }
    @PostMapping(path = "update")
    public @ResponseBody String update(@RequestParam int id,@RequestParam String message, @RequestParam int response){
        Sms sms  = smsRepository.findById(id).get();
        sms.setMessage(message);
        sms.setResponse(response);
        smsRepository.save(sms);
        return "Updated";
    }
    @PostMapping(path = "updatemessage")
    public @ResponseBody String updateMessage(@RequestParam int id,@RequestParam String message){
        Sms sms  = smsRepository.findById(id).get();
        sms.setMessage(message);
        smsRepository.save(sms);
        return "Updated";
    }
    @PostMapping(path = "updateresponse")
    public @ResponseBody String updateResponse(@RequestParam int id, @RequestParam int response){
        Sms sms  = smsRepository.findById(id).get();
        sms.setResponse(response);
        smsRepository.save(sms);
        return "Updated";
    }
    @GetMapping(path = "exist")
    public @ResponseBody boolean isSms(@RequestParam int id){
        return smsRepository.existsById(id);
    }
    @GetMapping(path = "size")
    public @ResponseBody long sizeSms(){
        return smsRepository.count();
    }
}

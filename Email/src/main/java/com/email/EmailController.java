package com.email;

import com.email.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/email")
public class EmailController {
    @Autowired
    EmailRepository emailRepository;
    @PostMapping(path = "new")
    public @ResponseBody String newEmail(@RequestParam String subject, @RequestParam String text, @RequestParam String filePath, @RequestParam int response){
        Email email = new Email(subject,text,filePath,response);
        emailRepository.save(email);
        return "Saved";
    }
    @GetMapping(path = "/allemail")
    public @ResponseBody Iterable<Email> getAllEmail(){
        return emailRepository.findAll();
    }
    @GetMapping(path = "/email")
    public @ResponseBody Optional<Email> getEmail(@RequestParam int id){
        return emailRepository.findById(id);
    }
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteEmail(@RequestParam int id) {
        emailRepository.deleteById(id);
        return "Deleted";
    }
    @PostMapping(path = "update")
    public @ResponseBody String update(@RequestParam int id,@RequestParam String subject, @RequestParam String text, @RequestParam String filePath, @RequestParam int response){
        Email email  = emailRepository.findById(id).get();
        email.setSubject(subject);
        email.setText(text);
        email.setFilePath(filePath);
        email.setResponse(response);
        emailRepository.save(email);
        return "Updated";
    }
    @PostMapping(path = "updatesubject")
    public @ResponseBody String updateSubject(@RequestParam int id,@RequestParam String subject){
        Email email  = emailRepository.findById(id).get();
        email.setSubject(subject);
        emailRepository.save(email);
        return "Updated";
    }
    @PostMapping(path = "updatetext")
    public @ResponseBody String updateText(@RequestParam int id, @RequestParam String text){
        Email email  = emailRepository.findById(id).get();
        email.setText(text);
        emailRepository.save(email);
        return "Updated";
    }
    @PostMapping(path = "updatefile")
    public @ResponseBody String updateFile(@RequestParam int id, @RequestParam String filePath){
        Email email  = emailRepository.findById(id).get();
        email.setFilePath(filePath);
        emailRepository.save(email);
        return "Updated";
    }
    @PostMapping(path = "updateresponse")
    public @ResponseBody String updateResponse(@RequestParam int id,@RequestParam int response){
        Email email  = emailRepository.findById(id).get();
        email.setResponse(response);
        emailRepository.save(email);
        return "Updated";
    }
    @GetMapping(path = "exist")
    public @ResponseBody boolean isEmail(@RequestParam int id){
        return emailRepository.existsById(id);
    }
    @GetMapping(path = "size")
    public @ResponseBody long sizeEmail(){
        return emailRepository.count();
    }
}

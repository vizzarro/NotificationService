package com.channel;

import com.channel.model.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/channel")
public class ChannelController {
    @Autowired
    ChannelRepository channelRepository;
    @PostMapping(path = "new")
    public @ResponseBody String newChannel(@RequestParam String name, @RequestParam String info, @RequestParam int numSub){
        Channel channel = new Channel(name, info, numSub);
        channelRepository.save(channel);
        return "Saved";
    }
    @GetMapping(path = "/channels")
    public @ResponseBody Iterable<Channel> getAllNotifications(){
        return channelRepository.findAll();
    }
    @GetMapping(path = "/channel")
    public @ResponseBody Optional<Channel> getNotification(@RequestParam int id){
        return channelRepository.findById(id);
    }
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteNotification(@RequestParam int id) {
        channelRepository.deleteById(id);
        return "Deleted";
    }
    @PostMapping(path = "update")
    public @ResponseBody String update(@RequestParam int id,@RequestParam String name, @RequestParam String info, @RequestParam int numSub){
        Channel channel  = channelRepository.findById(id).get();
        channel.setName(name);
        channel.setInfo(info);
        channel.setNumSub(numSub);
        channelRepository.save(channel);
        return "Updated";
    }
    @PostMapping(path = "updatename")
    public @ResponseBody String updateName(@RequestParam int id, @RequestParam String name){
        Channel channel  = channelRepository.findById(id).get();
        channel.setName(name);
        channelRepository.save(channel);
        return "Updated";
    }
    @PostMapping(path = "updateinfo")
    public @ResponseBody String updateInfo(@RequestParam int id, @RequestParam String info){
        Channel channel  = channelRepository.findById(id).get();
        channel.setInfo(info);
        channelRepository.save(channel);
        return "Updated";
    }
    @PostMapping(path = "updatenumsub")
    public @ResponseBody String updateInfo(@RequestParam int id, @RequestParam int numSub){
        Channel channel  = channelRepository.findById(id).get();
        channel.setNumSub(numSub);
        channelRepository.save(channel);
        return "Updated";
    }
    @PostMapping(path = "exist")
    public @ResponseBody boolean isChannel(@RequestParam int id){
        return channelRepository.existsById(id);
    }
    @PostMapping(path = "size")
    public @ResponseBody long sizeChannel(){
        return channelRepository.count();
    }

}

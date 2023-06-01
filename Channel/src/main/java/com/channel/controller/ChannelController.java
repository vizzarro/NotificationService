package com.channel.controller;

import com.channel.model.dto.ChannelDTO;
import com.channel.services.ChannelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/channel")
public class ChannelController {
    ChannelServices channelServices;

    @Autowired
    public ChannelController(ChannelServices channelServices) {
        this.channelServices = channelServices;
    }

    @PostMapping
    public ChannelDTO newChannel(@RequestBody ChannelDTO c) {
        return channelServices.save(c);
    }

    @GetMapping
    public @ResponseBody List<ChannelDTO> getAllChannels() {
        return channelServices.findAll();
    }

    @GetMapping(path = "{id}")
    public @ResponseBody ChannelDTO getChannel(@PathVariable(value = "id") int id) {
        return channelServices.findById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteNotification(@PathVariable(value = "id") int id) {
        channelServices.delete(id);
    }

    @PutMapping(path = "{id}")
    public ChannelDTO update(@PathVariable(value = "id") int id, @RequestBody ChannelDTO c) {
        c.setId(id);
        return channelServices.save(c);
    }

    @PatchMapping(path = "{id}/{name}")
    public void updateName(@PathVariable(value = "id") int id, @PathVariable(value = "name") String name) {
        ChannelDTO dto = channelServices.findById(id);
        dto.setName(name);
        channelServices.save(dto);
    }

    @GetMapping(path = "/channels")
    public ChannelDTO findByName(@RequestParam String name) {
        return channelServices.findByName(name);
    }


    @GetMapping(path = "count")
    public long countChannels() {
        return channelServices.count();
    }

}

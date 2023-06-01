package com.channel.services;

import com.channel.model.Channel;
import com.channel.model.dto.ChannelDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ChannelServices {

    private final ChannelRepository channelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ChannelServices(ChannelRepository channelRepository, ModelMapper modelMapper) {
        this.channelRepository = channelRepository;
        this.modelMapper = modelMapper;
    }

    public ChannelDTO save(ChannelDTO c) {
        Channel channel = modelMapper.map(c, Channel.class);
        channelRepository.save(channel);
        return modelMapper.map(channel, ChannelDTO.class);
    }

    public List<ChannelDTO> findAll() {
        return channelRepository.findAll().stream().map(e -> modelMapper.map(e, ChannelDTO.class)).collect(Collectors.toList());
    }

    public ChannelDTO findById(int id) {
        Channel channel = channelRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return modelMapper.map(channel, ChannelDTO.class);
    }

    public ChannelDTO findByName(String name) {
        Channel channel = channelRepository.findChannelByName(name);
        return modelMapper.map(channel, ChannelDTO.class);
    }

    public void delete(int id) {
        channelRepository.deleteById(id);
    }

    public long count() {
        return channelRepository.count();
    }

}

package com.channel;

import com.channel.model.Channel;
import org.springframework.data.repository.CrudRepository;

public interface ChannelRepository extends CrudRepository<Channel,Integer> { }
package com.channel.services;

import com.channel.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ChannelRepository extends JpaRepository<Channel,Integer> { }
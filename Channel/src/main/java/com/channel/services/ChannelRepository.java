package com.channel.services;

import com.channel.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {

    @Query(value = "select * FROM channel.channel where \"name\" = ?1", nativeQuery = true)
    Channel findChannelByName(String name);
}
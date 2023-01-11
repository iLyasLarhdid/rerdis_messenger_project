package com.larhdid.messenger.service;

import com.larhdid.messenger.entity.Message;
import com.larhdid.messenger.entity.dto.MessageDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RedisMessagePublisher {
    private RedisTemplate<String, Message> redisTemplate;
    private ChannelTopic topic;

    public void publish(MessageDto messageDto) {
        redisTemplate.convertAndSend("/messages/"+messageDto.getGroup(), messageDto.getMessage());
    }
}

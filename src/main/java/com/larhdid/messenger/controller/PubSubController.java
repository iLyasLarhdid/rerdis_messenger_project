package com.larhdid.messenger.controller;

import com.larhdid.messenger.entity.Message;
import com.larhdid.messenger.entity.dto.MessageDto;
import com.larhdid.messenger.service.RedisMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/messages")
public class PubSubController {
    private final RedisMessagePublisher messagePublisher;

    @GetMapping
    public Message sayHi(){
        return new Message("ilyas_lar","group","hello and welcome");
    }

    @PostMapping
    public void sendMessage(@RequestBody MessageDto messageDto){
        messagePublisher.publish(messageDto);
    }


}

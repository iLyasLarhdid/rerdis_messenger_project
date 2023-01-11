package com.larhdid.messenger.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RedisPubSubService implements MessageListener {
    public static List<String> messageList = new ArrayList<>();

    public void onMessage(Message message, byte[] pattern) {
        messageList.add(message.toString());
        System.out.println("Message received: " + message.toString());
    }

}
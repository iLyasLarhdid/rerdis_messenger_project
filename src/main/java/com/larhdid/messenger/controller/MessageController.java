package com.larhdid.messenger.controller;

import com.larhdid.messenger.entity.Message;
import com.larhdid.messenger.entity.dto.MessageDto;
import com.larhdid.messenger.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/{page}")
    public Page<Message> getAll(@PathVariable int page){
        return messageService.getAll(page);
    }
    @PostMapping
    public Message saveMessage(@RequestBody MessageDto messageDto){
        return messageService.save(messageDto);
    }
}

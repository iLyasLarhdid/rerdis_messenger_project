package com.larhdid.messenger.service;

import com.larhdid.messenger.Repository.UserMessageRepository;
import com.larhdid.messenger.entity.Message;
import com.larhdid.messenger.entity.dto.MessageDto;
import com.larhdid.messenger.properties.PaginationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final UserMessageRepository messageRepository;
    private final PaginationProperties paginationProperties;

    public Page<Message> getAll(int page){
        Pageable pageable = PageRequest.of(page,paginationProperties.getPageSize(), Sort.by("sentDate").descending());
        return messageRepository.findAll(pageable);
    }

    public Message save(MessageDto messageDto) {
        return messageRepository.save(new Message(messageDto.getUsername(),messageDto.getGroup(),messageDto.getMessage(), LocalDateTime.now()));
    }
}

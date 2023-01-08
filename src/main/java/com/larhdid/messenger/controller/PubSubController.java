package com.larhdid.messenger.controller;

import com.larhdid.messenger.entity.Message;
import com.larhdid.messenger.service.PubSubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PubSubController {
    private final PubSubService messagingService;

    @Value("${auth0.username-claim}")
    String usernameClaim;

    @ConnectMapping
    void onConnect(RSocketRequester requester) {
        Objects.requireNonNull(requester.rsocket(), "rsocket connection should not be null")
                .onClose()
                .doOnError(error -> log.warn(requester.rsocketClient() + " Closed"))
                .doFinally(consumer -> log.info(requester.rsocketClient() + " Disconnected"))
                .subscribe();
    }
    @MessageMapping("publish")
    Mono<Void> publish(Message message) {
        return messagingService.publish(message);
    }

    @MessageMapping("subscribe")
    Flux<Message> subscribe() {
        return messagingService.subscribe();
    }

}

package com.larhdid.messenger.service;

import com.larhdid.messenger.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class PubSubService {
    private final ReactiveRedisTemplate<String, Message> reactiveTemplate;
    private final ReactiveRedisMessageListenerContainer reactiveMsgListenerContainer;

    private final ChannelTopic channelTopic = new ChannelTopic("broadcast"); // channel used to send and receive messages

    public Mono<Void> publish(Message message) {
        return this.reactiveTemplate
                .convertAndSend(channelTopic.getTopic(), message)
                .then(Mono.empty());
    }

    public Flux<Message> subscribe() {
        return reactiveMsgListenerContainer
                .receive(Collections.singletonList(channelTopic),
                        reactiveTemplate.getSerializationContext().getKeySerializationPair(),
                        reactiveTemplate.getSerializationContext().getValueSerializationPair())
                .map(ReactiveSubscription.Message::getMessage);
    }
}

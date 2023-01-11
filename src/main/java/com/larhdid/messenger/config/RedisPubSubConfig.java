package com.larhdid.messenger.config;

import com.larhdid.messenger.entity.Message;
import com.larhdid.messenger.service.RedisPubSubService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisPubSubConfig {
    @Bean
    public RedisTemplate<String, Message> redisTemplate() {
        final RedisTemplate<String, Message> template = new RedisTemplate<String, Message>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<Message>(Message.class));
        return template;
    }
    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisPubSubService());
    }
    @Bean
    RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        return container;
    }
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }
    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("messageQueue");
    }


}

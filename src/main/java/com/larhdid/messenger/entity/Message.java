package com.larhdid.messenger.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RedisHash("Message")
@Getter
@Setter
@EqualsAndHashCode
public class Message  implements Serializable {
    @Id
    @GenericGenerator(name = "message_uuid",strategy = "uuid")
    @GeneratedValue(generator = "message_uuid")
    private String id;
    private String username;
    private String group;
    @Column(length = 10485760)
    private String message;
    private LocalDateTime sentDate;
    @ManyToOne
    private Conversation conversation;
    @ManyToOne
    private AppUser sender;

    public Message(String username, String group, String message, LocalDateTime sentDate) {
        this.username = username;
        this.group = group;
        this.message = message;
        this.sentDate = sentDate;
    }
}
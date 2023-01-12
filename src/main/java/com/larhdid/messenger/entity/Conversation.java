package com.larhdid.messenger.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;


@Entity
@Table(name = "conversations")
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Conversation {
    @Id
    @GenericGenerator(name = "conversation_uuid",strategy = "uuid")
    @GeneratedValue(generator = "conversation_uuid")
    private String id;
    private String title;
    private long createdAt;
    private long updatedAt;

    @ManyToOne
    private AppUser creator;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Message> messages;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Participants> participants;
}

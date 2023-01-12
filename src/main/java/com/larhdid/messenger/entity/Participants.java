package com.larhdid.messenger.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "participants")
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Participants {
    @Id
    @GenericGenerator(name = "participant_uuid",strategy = "uuid")
    @GeneratedValue(generator = "participant_uuid")
    private String id;
    private boolean isLastMessageBeenSeen;

    @ManyToOne
    private Conversation conversation;
    @ManyToOne
    private AppUser user;

    public Participants(Conversation conversation, AppUser user, boolean isLastMessageBeenSeen) {
        this.conversation = conversation;
        this.user = user;
        this.isLastMessageBeenSeen = isLastMessageBeenSeen;
    }
}

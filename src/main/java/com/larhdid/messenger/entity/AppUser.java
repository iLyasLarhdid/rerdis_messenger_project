package com.larhdid.messenger.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "forums")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AppUser {
    @Id
    @GenericGenerator(name = "user_uuid",strategy = "uuid")
    @GeneratedValue(generator = "user_uuid")
    private String id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    private String avatar = null;
    private LocalDateTime userDate;

    @OneToMany(mappedBy = "creator")
    @JsonIgnore
    private Set<Conversation> conversations;
    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private Set<Message> messages;//instead of havinf messages like this (messages should come from the redist getAll() which returns a page of messages
}

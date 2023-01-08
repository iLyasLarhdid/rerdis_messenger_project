package com.larhdid.messenger.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.larhdid.messenger.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String userName;
    private String email;
    private String password;
    private UserRole role;


    @JsonIgnore
    private boolean locked = false;
    @JsonIgnore
    private boolean enabled = false;
    @JsonIgnore
    private Boolean accountExpired = false;
    @JsonIgnore
    private Boolean credentialsExpired = false;

    @ManyToMany
    private Set<Group> groups;
    @OneToMany
    private List<Message> messeges;

    public User(String userName, String email, String password, UserRole role) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

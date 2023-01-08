package com.larhdid.messenger.entity.dto;

import com.larhdid.messenger.entity.Group;
import com.larhdid.messenger.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class UserDto {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private Set<Group> groups;
    private List<Message> messeges;

    public UserDto(Long id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}

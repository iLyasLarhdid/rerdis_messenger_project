package com.larhdid.messenger.service;

import com.larhdid.messenger.entity.User;
import com.larhdid.messenger.entity.dto.UserDto;
import com.larhdid.messenger.entity.enums.UserRole;
import com.larhdid.messenger.properties.PaginationProperties;
import com.larhdid.messenger.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PaginationProperties paginationProperties;

    public Page<User> getUsersPage(int page){
        Pageable pageable = PageRequest.of(page,paginationProperties.getPageSize(), Sort.by("userDate").ascending());
        return userRepository.findAllByEnabledIsTrue(pageable);
    }

    public User save(UserDto userDto){
        return userRepository.save(new User(userDto.getUserName(),userDto.getEmail(),passwordEncoder.encode(userDto.getPassword()), UserRole.ADMIN));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow();
    }
}

package com.spyrosoft.campfire.services;

import com.spyrosoft.campfire.data.UserCreateRequest;
import com.spyrosoft.campfire.entities.UserEntity;
import com.spyrosoft.campfire.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEntity readUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(EntityExistsException::new);
    }

    public void createUser(UserCreateRequest userCreateRequest) {
        UserEntity user = new UserEntity();
        Optional<UserEntity> byUsername = userRepository.findByUsername(userCreateRequest.getUsername());
        if (byUsername.isPresent()) {
            throw new RuntimeException("User already registered. Please use different username.");
        }
        user.setUsername(userCreateRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userCreateRequest.getPassword()));
        user.setAuthority(userCreateRequest.getAuthority());
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = readUserByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), getAuthorities(userEntity.getAuthority().toString()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String authority) {
        return List.of(new SimpleGrantedAuthority(authority));
    }
}

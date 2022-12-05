//package com.spyrosoft.campfire;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityExistsException;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserService {
//
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public UserEntity readUserByUsername(String username) {
//        return userRepository.findByUsername(username).orElseThrow(EntityExistsException::new);
//    }
//
//    public void createUser(UserCreateRequest userCreateRequest) {
//        UserEntity user = new UserEntity();
//        Optional<UserEntity> byUsername = userRepository.findByUsername(userCreateRequest.getUsername());
//        if (byUsername.isPresent()) {
//            throw new RuntimeException("User already registered. Please use different username.");
//        }
//        user.setUsername(userCreateRequest.getUsername());
//        user.setPassword(userCreateRequest.getPassword());
//        user.setAuthority(userCreateRequest.getAuthority());
//        userRepository.save(user);
//    }
//}

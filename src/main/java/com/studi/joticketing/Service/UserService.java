package com.studi.joticketing.Service;

import com.studi.joticketing.DTO.UserResponse;
import com.studi.joticketing.Mapper.UserMapper;
import com.studi.joticketing.Repository.UserRepository;
import com.studi.joticketing.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toUserResponse)
                .orElseThrow();
    }

    public UserResponse getUserByUserName(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper::toUserResponse)
                .orElseThrow();
    }

    public String saveUser(UserResponse userResponse) {
        User user = User.builder()
                .firstName(userResponse.getFirstName().substring(0, 1).toUpperCase() + userResponse.getFirstName().substring(1).toLowerCase())
                .lastName(userResponse.getLastName().toUpperCase())
                .username(userResponse.getUsername())
                .password(userResponse.getPassword())
                .role("USER")
                .user_key(UUID.randomUUID().toString())
                .build();
         userRepository.save(user);

         return "User "+ user.getLastName() + " " + user.getFirstName() + " created successfully";
    }
}

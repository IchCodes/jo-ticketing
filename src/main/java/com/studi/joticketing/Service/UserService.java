package com.studi.joticketing.Service;

import com.studi.joticketing.DTO.UserResponse;
import com.studi.joticketing.Mapper.UserMapper;
import com.studi.joticketing.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

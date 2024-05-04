package com.studi.joticketing.Controller;

import com.studi.joticketing.DTO.UserResponse;
import com.studi.joticketing.Service.UserService;
import com.studi.joticketing.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class Authentication {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserResponse userResponse) {
        return ResponseEntity.ok(userService.saveUser(userResponse));
    }

    @PostMapping("/login")
    public String login() {
        return "Login";
    }
}

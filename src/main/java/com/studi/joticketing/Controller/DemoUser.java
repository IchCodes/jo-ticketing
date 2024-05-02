package com.studi.joticketing.Controller;

import com.studi.joticketing.DTO.UserResponse;
import com.studi.joticketing.Service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class DemoUser {

    private final UserService userService;

    @GetMapping("id/{id}")
    public String getUserById(@PathVariable Long id) {
        var result = userService.getUserById(id);
        return "Hello " + result.getLastName() + " " + result.getFirstName() + ", your role is " + result.getRole();
    }

    @GetMapping("idResponseEntity/{id}")
    public ResponseEntity<String> getUserByIdEntity(@PathVariable Long id) {
        var result = userService.getUserById(id);
        return ResponseEntity.ok("Hello " + result.getLastName() + " " + result.getFirstName() + ", your role is " + result.getRole());
    }

    @GetMapping("username/{username}")
    public String getUserByUsername(@PathVariable String username) {
        var result = userService.getUserByUserName(username);
        return "Hello " + result.getLastName() + " " + result.getFirstName() + ", your role is " + result.getRole();
    }

    @GetMapping("usernameResponseEntity/{username}")
    public ResponseEntity<UserResponse> getUserByUsernameResponseEntity(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUserName(username));
    }
}

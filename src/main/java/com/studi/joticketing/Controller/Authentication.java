package com.studi.joticketing.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class Authentication {

    @PostMapping("/register")
    public String register() {
        return "Register";
    }

    @PostMapping("/login")
    public String login() {
        return "Login";
    }
}

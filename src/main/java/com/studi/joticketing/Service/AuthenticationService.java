package com.studi.joticketing.Service;

import com.studi.joticketing.Repository.UserRepository;
import com.studi.joticketing.exception.WrongCredentialsException;
import com.studi.joticketing.model.AuthenticationResponse;
import com.studi.joticketing.model.Role;
import com.studi.joticketing.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(User request) {

        // check if user already exist. if exist than authenticate the user
        if(repository.findByUsername(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null,null,null,null, "User already exist");
        }

        User user = User.builder()
                .firstName(request.getFirstName().substring(0, 1).toUpperCase() + request.getFirstName().substring(1).toLowerCase())
                .lastName(request.getLastName().toUpperCase())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .user_key(UUID.randomUUID().toString())
                .build();

        user = repository.save(user);


        return new AuthenticationResponse(user.getFirstName(), user.getLastName(),user.getRole(),null, "User "+ user.getFirstName() + " "+ user.getLastName() +"  registration was successful");

    }

    public AuthenticationResponse authenticate(User request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new WrongCredentialsException("Invalid username or password");
        }

        User user = repository.findByUsername(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        return new AuthenticationResponse(user.getFirstName(), user.getLastName(),user.getRole(),jwt, "User "+ user.getFirstName() + " "+ user.getLastName() +" login was successful");

    }
}


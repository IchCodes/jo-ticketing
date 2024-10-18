package com.studi.joticketing.Service;

import com.studi.joticketing.Repository.UserRepository;
import com.studi.joticketing.exception.WrongCredentialsException;
import com.studi.joticketing.model.AuthenticationResponse;
import com.studi.joticketing.model.Role;
import com.studi.joticketing.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUserAlreadyExists() {
        User user = new User();
        user.setUsername("existingUser");

        when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(user));

        AuthenticationResponse response = authenticationService.register(user);

        assertEquals("User already exist", response.getMessage());
    }

    @Test
    public void testRegisterNewUser() {
        User user = new User();
        user.setUsername("newUser");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");

        when(userRepository.findByUsername("newUser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AuthenticationResponse response = authenticationService.register(user);

        assertEquals("User John DOE registration was successful", response.getMessage());
    }


    @Test
    public void testAuthenticateFailure() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("wrongPassword");

        doThrow(AuthenticationException.class).when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        assertThrows(WrongCredentialsException.class, () -> authenticationService.authenticate(user));
    }
}
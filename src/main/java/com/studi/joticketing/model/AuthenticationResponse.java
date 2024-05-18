package com.studi.joticketing.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponse {
    private final String firstName;
    private final String lastName;
    private final Role role;
    private final String token;
    private final String message;


}

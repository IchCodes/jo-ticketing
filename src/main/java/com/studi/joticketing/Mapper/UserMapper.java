package com.studi.joticketing.Mapper;

import com.studi.joticketing.DTO.UserResponse;
import com.studi.joticketing.model.User;


public class UserMapper {
    public static UserResponse toUserResponse(User user) {
        UserResponse UserResponse = new UserResponse();
        UserResponse.setFirstName(user.getFirstName());
        UserResponse.setLastName(user.getLastName());
        UserResponse.setRole(user.getRole());
        UserResponse.setCustomField1("Message customisé");
        return UserResponse;
    }
}

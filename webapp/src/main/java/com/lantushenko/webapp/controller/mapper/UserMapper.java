package com.lantushenko.webapp.controller.mapper;

import com.lantushenko.webapp.controller.dto.AuthenticatedUser;
import com.lantushenko.webapp.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public AuthenticatedUser toAuthenticatedUser(User user){
        return AuthenticatedUser.builder()
                .id(user.getId())
                .fullName(user.getName())
                .username(user.getUsername())
                .build();
    }
}

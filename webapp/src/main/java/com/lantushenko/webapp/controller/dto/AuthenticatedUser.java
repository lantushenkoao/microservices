package com.lantushenko.webapp.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticatedUser {
    private String id;
    private String username;
    private String fullName;
}

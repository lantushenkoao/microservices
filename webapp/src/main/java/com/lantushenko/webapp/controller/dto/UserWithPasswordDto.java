package com.lantushenko.webapp.controller.dto;

import lombok.Data;

@Data
public class UserWithPasswordDto extends UserDto{
    private String password;
}

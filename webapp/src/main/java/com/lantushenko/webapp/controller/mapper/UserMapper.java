package com.lantushenko.webapp.controller.mapper;

import com.lantushenko.webapp.controller.dto.UserDto;
import com.lantushenko.webapp.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract UserDto toUserDto(User user);
}

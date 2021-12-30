package com.lantushenko.webapp.controller.mapper;

import com.lantushenko.webapp.controller.dto.UserDto;
import com.lantushenko.webapp.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract UserDto toUserDto(User user);
    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    public abstract void update(UserDto request, @MappingTarget User user);

}

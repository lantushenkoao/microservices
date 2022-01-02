package com.lantushenko.webapp.controller.mapper;

import com.lantushenko.webapp.controller.dto.UserDto;
import com.lantushenko.webapp.controller.dto.UserWithPasswordDto;
import com.lantushenko.webapp.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-02T21:10:18+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.3.1.jar, environment: Java 11.0.4 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public User create(UserWithPasswordDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setName( userDto.getName() );
        user.setLogin( userDto.getLogin() );
        user.setEmail( userDto.getEmail() );
        user.setPassword( userDto.getPassword() );

        return user;
    }

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setName( user.getName() );
        userDto.setLogin( user.getLogin() );
        userDto.setEmail( user.getEmail() );

        return userDto;
    }

    @Override
    public void update(UserDto request, User user) {
        if ( request == null ) {
            return;
        }

        if ( request.getId() != null ) {
            user.setId( request.getId() );
        }
        if ( request.getName() != null ) {
            user.setName( request.getName() );
        }
        if ( request.getLogin() != null ) {
            user.setLogin( request.getLogin() );
        }
        if ( request.getEmail() != null ) {
            user.setEmail( request.getEmail() );
        }
    }
}

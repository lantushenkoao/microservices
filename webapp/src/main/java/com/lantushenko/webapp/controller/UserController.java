package com.lantushenko.webapp.controller;

import com.lantushenko.webapp.controller.dto.UserDto;
import com.lantushenko.webapp.controller.mapper.UserMapper;
import com.lantushenko.webapp.model.User;
import com.lantushenko.webapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    //@Secured({ADMIN})
    @GetMapping
    public ResponseEntity<List<UserDto>> listUser(){
        List<UserDto> users = userService.listUsers().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .header("X-Total-Count", Integer.toString(users.size()))
                .body(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable(value = "userId") String userId){
        UserDto user = userMapper.toUserDto(userService.loadUserById(userId));

        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    public UserDto editUser(@PathVariable String id, @RequestBody UserDto userDto){
        User existingUser = userService.loadUserById(id);
        userMapper.update(userDto, existingUser);
        userService.save(existingUser);
        return userMapper.toUserDto(existingUser);
    }
}

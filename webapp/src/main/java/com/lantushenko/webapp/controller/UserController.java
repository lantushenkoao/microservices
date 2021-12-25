package com.lantushenko.webapp.controller;

import com.lantushenko.webapp.controller.dto.UserDto;
import com.lantushenko.webapp.controller.mapper.UserMapper;
import com.lantushenko.webapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    //@Secured({ADMIN})
    @GetMapping("list")
    public ResponseEntity<List<UserDto>> listUser(){
        List<UserDto> users = userService.listUsers().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(users);
    }
}

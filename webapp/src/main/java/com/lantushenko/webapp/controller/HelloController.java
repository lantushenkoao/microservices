package com.lantushenko.webapp.controller;

import com.lantushenko.webapp.controller.dto.HelloResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping("user")
    public HelloResponse helloUser() {
        return new HelloResponse("Greetings from Spring Boot for user!");
    }

    @GetMapping("admin")
    public String helloAdmin() {
        return "Greetings from Spring Boot for admin!";
    }
}

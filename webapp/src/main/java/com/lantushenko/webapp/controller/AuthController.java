package com.lantushenko.webapp.controller;

import com.lantushenko.webapp.auth.AuthenticatedUserDetails;
import com.lantushenko.webapp.auth.JwtTokenUtil;
import com.lantushenko.webapp.controller.dto.AuthRequest;
import com.lantushenko.webapp.controller.dto.AuthenticatedUser;
import com.lantushenko.webapp.controller.mapper.UserMapper;
import com.lantushenko.webapp.model.User;
import com.lantushenko.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/auth")
public class AuthController {

    @Value("${jwt.cookie-name}")
    private String jwtCookieName;
    @Resource
    private UserService userService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserMapper userMapper;
    @Value("${jwt.CookieExpirationDays}")
    private int jwtCookieExpirationDays;


    @PostMapping("login")
    public ResponseEntity<AuthenticatedUser> login(@RequestBody @Valid AuthRequest request, HttpServletResponse response) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            AuthenticatedUserDetails authenticatedUser = (AuthenticatedUserDetails) authenticate.getPrincipal();
            String jwtToken = jwtTokenUtil.generateAccessToken(authenticatedUser);

            Cookie cookie = new Cookie(jwtCookieName, jwtToken);
            cookie.setMaxAge(jwtCookieExpirationDays * 24 * 60 * 60);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);


            return ResponseEntity.ok().build();
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

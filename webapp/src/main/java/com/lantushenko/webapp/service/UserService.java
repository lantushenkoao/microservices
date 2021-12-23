package com.lantushenko.webapp.service;

import com.lantushenko.webapp.auth.AuthenticatedUserDetails;
import com.lantushenko.webapp.model.User;
import com.lantushenko.webapp.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (null == user) {
            throw new UsernameNotFoundException(null);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>(getGrantedAuthority(user));

        return new AuthenticatedUserDetails(user.getPassword(), user.getUsername(), true, true, authorities, true, true);
    }

    private List<SimpleGrantedAuthority> getGrantedAuthority(User user) {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName())));

        return authorities;
    }

}

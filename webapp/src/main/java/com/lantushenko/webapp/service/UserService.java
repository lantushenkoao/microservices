package com.lantushenko.webapp.service;

import com.lantushenko.webapp.auth.AuthenticatedUserDetails;
import com.lantushenko.webapp.model.Role;
import com.lantushenko.webapp.model.User;
import com.lantushenko.webapp.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private PermissionService permissionService;

    public List<User> listUsers(){
        permissionService.checkRole(Role.RoleName.ADMIN);
        return userRepository.findAll();
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    @Transactional
    public void delete(User user){
        userRepository.delete(user);
    }
    public User loadUser(String login){
        return userRepository.findByLogin(login);
    }

    public User loadUserById(String id){
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);

        if (null == user) {
            throw new UsernameNotFoundException(null);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>(getGrantedAuthority(user));

        return new AuthenticatedUserDetails(user.getId(), user.getPassword(), user.getLogin(), true, true, authorities, true, true);
    }

    private List<SimpleGrantedAuthority> getGrantedAuthority(User user) {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName())));

        return authorities;
    }

}

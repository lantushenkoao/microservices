package com.lantushenko.webapp.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {


    public void checkRole(String roleName){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(null == auth){
            throw new AccessDeniedException("Your session is not valid");
        }
        boolean hasRole = auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equalsIgnoreCase(roleName));
        if(!hasRole){
            throw new AccessDeniedException(String.format("You do not have %s role to access this resource", roleName));
        }
    }
}

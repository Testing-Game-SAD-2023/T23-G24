package com.g24.authentication.model.service.impl;

import org.springframework.stereotype.Service;

//import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.g24.authentication.model.service.AuthService;
//import com.g24.authentication.entity.User;
//import com.g24.authentication.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService
{
	//@Autowired private UserRepository userRepository;

	@Override
	public boolean isAdmin()
	{
		if((SecurityContextHolder.getContext().getAuthentication().getAuthorities()).contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
			return true;
		else
			return false;
	}
	
	@Override
    public boolean isUserAuthenticated() //Authentication authentication) 
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        
        return true;
    }
	
}



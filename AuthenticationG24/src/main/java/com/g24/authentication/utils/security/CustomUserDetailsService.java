package com.g24.authentication.utils.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import com.g24.authentication.model.entity.Role;
import com.g24.authentication.model.entity.User;
import com.g24.authentication.model.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	private UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{
		User user = userRepository.findByEmail(email);
		
		if(user != null)
		{
			if (!user.isEnabled())
				throw new UsernameNotFoundException("User not enabled.");
			else
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
		}
		else
		{
			throw new UsernameNotFoundException("Invalid username or password.");
		}
	}
	
	private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles)
	{
		Collection < ? extends GrantedAuthority> mapRoles = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
		return mapRoles;
	}
}
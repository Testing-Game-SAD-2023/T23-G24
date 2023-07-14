package com.g24.authentication.model.service.impl;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.g24.authentication.model.entity.User;
import com.g24.authentication.model.repository.UserRepository;
import com.g24.authentication.model.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired private UserRepository userRepository;
	@Autowired private PasswordEncoder passwordEncoder;
	
	@Override
	public User findUserByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}	
	
	@Override
	public List<User> findAllUsers() 
	{ 
		return userRepository.findByEnabledTrue();
	}

	@Override
	public boolean matchPassword(User user, String password)
	{
        String encryptedPassword = user.getPassword();
        boolean isPasswordRight = passwordEncoder.matches(password, encryptedPassword);
        return isPasswordRight; 
	}
	
}
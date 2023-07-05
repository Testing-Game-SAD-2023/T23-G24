package com.g24.authentication.model.service;

import java.util.List;

import com.g24.authentication.model.entity.User;

public interface UserService
{
	User findUserByEmail(String email);
	
	List<User> findAllUsers();

	
	boolean matchPassword(User user, String password);
}
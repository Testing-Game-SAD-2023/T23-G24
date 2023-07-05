package com.g24.authentication.model.service;

import com.g24.authentication.model.entity.Token;
import com.g24.authentication.model.entity.User;
import com.g24.authentication.utils.exceptions.TokenException;
import com.g24.authentication.utils.exceptions.UserException;

import jakarta.servlet.http.HttpServletRequest;

public interface RegisterService
{
	//User findUserByEmail(String email);
	
	//Token findByToken(String token);

	void registerUser(User user, String password/*, HttpServletRequest request*/) throws UserException;
	
	//void deleteUser(User user);
    
    void enableUser(String token) throws TokenException;
}
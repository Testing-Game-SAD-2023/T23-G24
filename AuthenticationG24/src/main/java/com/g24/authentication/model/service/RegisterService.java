package com.g24.authentication.model.service;

import com.g24.authentication.model.entity.User;
import com.g24.authentication.utils.exceptions.TokenException;
import com.g24.authentication.utils.exceptions.UserException;

public interface RegisterService
{
	void registerUser(User user, String password) throws UserException;

	void enableUser(String token) throws TokenException;
}
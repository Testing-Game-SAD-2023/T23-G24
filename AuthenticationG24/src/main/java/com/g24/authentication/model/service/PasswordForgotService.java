package com.g24.authentication.model.service;

import com.g24.authentication.utils.exceptions.TokenException;
import com.g24.authentication.utils.exceptions.UserException;

public interface PasswordForgotService
{
	void generatePasswordResetToken(String email) throws UserException, TokenException;
}
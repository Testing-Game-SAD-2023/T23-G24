package com.g24.authentication.model.service;

import com.g24.authentication.utils.exceptions.PasswordException;
import com.g24.authentication.utils.exceptions.TokenException;

public interface PasswordResetService
{
	String findPasswordResetToken(String token) throws TokenException;

	void passwordReset(String token, String newPassword) throws PasswordException;
}
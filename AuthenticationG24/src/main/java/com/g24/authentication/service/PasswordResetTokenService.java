package com.g24.authentication.service;

import com.g24.authentication.entity.PasswordResetToken;

import com.g24.authentication.entity.User;

public interface PasswordResetTokenService
{
	PasswordResetToken findByToken(String token);
	
	PasswordResetToken findByUser(User user);

	void delete(PasswordResetToken token);

	void save(PasswordResetToken token);

}
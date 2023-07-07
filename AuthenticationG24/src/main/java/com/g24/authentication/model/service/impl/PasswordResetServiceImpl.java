package com.g24.authentication.model.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.g24.authentication.model.entity.Token;
import com.g24.authentication.model.entity.User;
import com.g24.authentication.model.repository.TokenRepository;
import com.g24.authentication.model.repository.UserRepository;
import com.g24.authentication.model.service.PasswordResetService;
import com.g24.authentication.utils.exceptions.PasswordException;
import com.g24.authentication.utils.exceptions.TokenException;

//Annotazione Service - Questa classe è un servizio Spring che deve essere analizzato dal framework Spring per l'inserimento delle dipendenze.
@Service
public class PasswordResetServiceImpl implements PasswordResetService
{
	@Autowired private UserRepository userRepository;
	@Autowired private TokenRepository tokenRepository;
	@Autowired private PasswordEncoder passwordEncoder;
	
    @Override

    public String findPasswordResetToken(String token) throws TokenException
    {
    	Token resetToken = tokenRepository.findByToken(token);
    	
    	if(resetToken != null)
    	{
    		if (!resetToken.getType().equals("PasswordReset"))
    		{
    			throw new TokenException("This token can't be used for password reset.");
    		}
    		else if (resetToken.isExpired())
			{
    			throw new TokenException("Token has expired, please request a new password reset.");
			}
			else {
				return resetToken.getToken();
			}
    		
    	}
    	else
    	{
    		throw new TokenException("Could not find password reset token.");
    	}
		
    }
    
    @Transactional
	public void passwordReset(String token, String newPassword) throws PasswordException
	{
		Token resetToken = tokenRepository.findByToken(token);
		
		User user = resetToken.getUser();

		if (passwordEncoder.matches(newPassword, user.getPassword()))
		{
			throw new PasswordException("New password can't be equal to the old one");
		}

		String passwordEncrypt = passwordEncoder.encode(newPassword);
		
		userRepository.updatePassword(passwordEncrypt, user.getId());

		tokenRepository.delete(resetToken);
	}
}
package com.g24.authentication.model.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.g24.authentication.model.entity.Mail;
import com.g24.authentication.model.entity.Token;
import com.g24.authentication.model.entity.User;
import com.g24.authentication.model.repository.TokenRepository;
import com.g24.authentication.model.repository.UserRepository;
import com.g24.authentication.model.service.EmailService;
import com.g24.authentication.model.service.PasswordForgotService;

import com.g24.authentication.utils.exceptions.TokenException;
import com.g24.authentication.utils.exceptions.UserException;


@Service
public class PasswordForgotServiceImpl implements PasswordForgotService
{
	@Autowired private UserRepository userRepository;
	@Autowired private TokenRepository tokenRepository;
	@Autowired private EmailService emailService;

	@Override
	public void generatePasswordResetToken(String email) throws UserException, TokenException
	{
		User user = userRepository.findByEmail(email);

		if (user == null)
		{
			throw new UserException("We could not find an user for that e-mail address.");
		}

		Token token = tokenRepository.findByUser(user);

		if(token !=null && !token.getType().equals("PasswordReset")) 
		{
			throw new TokenException("You must confirm your registration before you can change your password.");
		}

		if(token != null && token.isExpired())
		{
			tokenRepository.delete(token);
			token = null;
		}

		if (token == null) 
		{        
			token = new Token(null, UUID.randomUUID().toString(), "PasswordReset",user, null);
			token.setExpiryDate(30);
			token = tokenRepository.save(token);
		}

		Map<String, Object> modelMail = new HashMap<>();
		modelMail.put("token", token.getToken());
		modelMail.put("user", user.getFirstName());

		Mail mail = emailService.createEmail("PasswordReset", user.getEmail(), modelMail);
		emailService.sendEmail(mail);
	}
}
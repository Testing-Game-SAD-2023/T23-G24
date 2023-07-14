package com.g24.authentication.model.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.g24.authentication.model.entity.Mail;
import com.g24.authentication.model.entity.Role;
import com.g24.authentication.model.entity.Token;
import com.g24.authentication.model.entity.User;
import com.g24.authentication.model.repository.RoleRepository;
import com.g24.authentication.model.repository.TokenRepository;
import com.g24.authentication.model.repository.UserRepository;
import com.g24.authentication.model.service.EmailService;
import com.g24.authentication.model.service.RegisterService;
import com.g24.authentication.utils.exceptions.TokenException;
import com.g24.authentication.utils.exceptions.UserException;

@Service
public class RegisterServiceImpl implements RegisterService
{
	@Autowired private UserRepository userRepository;
	@Autowired private RoleRepository roleRepository;
	@Autowired private TokenRepository tokenRepository;
	@Autowired private PasswordEncoder passwordEncoder;

	@Autowired private EmailService emailService;

	@Override
	public void registerUser(User user, String password) throws UserException
	{		
		User existingUser = userRepository.findByEmail(user.getEmail());

		if(existingUser != null)
		{
			Token token = tokenRepository.findByUser(existingUser);

			if(token != null && token.getType().equals("Registration") && token.isExpired()) 
			{
				tokenRepository.delete(token);
				userRepository.delete(existingUser);
			}

			else
			{
				throw new UserException("There is already an user registered with the same email");
			}
		}

		user.setPassword(passwordEncoder.encode(password));

		Role role = roleRepository.findByName("ROLE_USER");

		user.setRoles(Arrays.asList(role));
		userRepository.save(user);

		Token token = new Token(null, UUID.randomUUID().toString(), "Registration",user, null);
		token.setExpiryDate(30);
		token = tokenRepository.save(token);

		Map<String, Object> modelMail = new HashMap<>();
		modelMail.put("token", token.getToken());
		modelMail.put("user", user.getFirstName());

		Mail mail = emailService.createEmail("Registration", user.getEmail(), modelMail);	
		emailService.sendEmail(mail);
	}

	@Override
	@Transactional
	public void enableUser(String token) throws TokenException
	{
		Token regToken = tokenRepository.findByToken(token);

		if (regToken != null)
		{
			if (regToken.getType().equals("Registration"))
			{
				if(!regToken.isExpired()) {
					tokenRepository.delete(regToken);
					userRepository.enable(regToken.getUser().getId());
				}
				else {
					throw new TokenException("Token has expired, please request a new registration.");
				}
			}
			else {
				throw new TokenException("This token can't be used for registration.");
			}
		}
		else 
			throw new TokenException("Could not find registration token.");
	}
}
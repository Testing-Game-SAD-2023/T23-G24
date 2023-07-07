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
//import com.g24.authentication.model.service.TokenService;
import com.g24.authentication.utils.exceptions.TokenException;
import com.g24.authentication.utils.exceptions.UserException;

import jakarta.servlet.http.HttpServletRequest;

//Annotazione Service - Questa classe è un servizio Spring che deve essere analizzato dal framework Spring per l'inserimento delle dipendenze.
@Service
public class RegisterServiceImpl implements RegisterService
{
	@Autowired private UserRepository userRepository;
	@Autowired private RoleRepository roleRepository;
	@Autowired private TokenRepository tokenRepository;
	@Autowired private PasswordEncoder passwordEncoder;

	@Autowired private EmailService emailService;
	//@Autowired private TokenService tokenService;

	/*
	 * @Override public User findUserByEmail(String email) { return
	 * userRepository.findByEmail(email); }
	 */	

	//saveUser - Crea una nuova entità per salvarla nel DB
	@Override
	public void registerUser(User user, String password/*, HttpServletRequest request*/) throws UserException
	{		
		User existingUser = userRepository.findByEmail(user.getEmail());//findUserByEmail(user.getEmail());

		if(existingUser != null)
		{
			Token token = tokenRepository.findByUser(existingUser);

			if(token != null && token.getType().equals("Registration") && token.isExpired()) 
			{
				tokenRepository.delete(token);
				userRepository.delete(existingUser); //deleteUser(existingUser);
			}

			else
			{
				throw new UserException("There is already an account registered with the same email");
			}
		}

		//Crittografia della password utilizzando Spring Security
		user.setPassword(passwordEncoder.encode(password));

		Role role = roleRepository.findByName("ROLE_USER");

		user.setRoles(Arrays.asList(role));
		userRepository.save(user);

		//Token token = tokenService.createToken(user, "Registration");
		Token token = new Token(null, UUID.randomUUID().toString(), "Registration",user, null);
		token.setExpiryDate(30);
		token = tokenRepository.save(token);
		/*
		 * Token token = new Token(); token.setToken(UUID.randomUUID().toString());
		 * token.setUser(user); token.setType("Registration"); token.setExpiryDate(30);
		 * tokenRepository.save(token);
		 */

		/*		Mail mail = new Mail();
		mail.setType("Registration");
		mail.setFrom("franceware00@gmail.com");
		mail.setTo(user.getEmail());
		mail.setSubject("Registration request");*/

		Map<String, Object> modelMail = new HashMap<>();
		modelMail.put("token", token.getToken());
		modelMail.put("user", user.getFirstName());

		/*
		 * String url = request.getScheme() + "://" + request.getServerName() + ":" +
		 * request.getServerPort(); modelMail.put("resetUrl", url +
		 * "/confirm-registration?token=" + token.getToken()); mail.setModel(modelMail);
		 */

		Mail mail = emailService.createEmail("Registration", user.getEmail(), modelMail);	
		emailService.sendEmail(mail);
	}

	@Override
	@Transactional
	public void enableUser(String token) throws TokenException
	{
		Token resetToken = tokenRepository.findByToken(token);//findByToken(token);	

		if (resetToken != null)
		{
			if (resetToken.getType().equals("Registration"))
			{
				tokenRepository.delete(resetToken);
				userRepository.enable(resetToken.getUser().getId());
			}
			else {
				throw new TokenException("This token can't be used for registration.");
			}
		}
		else 
			throw new TokenException("Could not find registration token.");
	}

	/*
	 * public Token findByToken(String token) {
	 * 
	 * return tokenRepository.findByToken(token);
	 * 
	 * }
	 */

	/*
	 * @Override priavte void deleteUser(User user) { userRepository.delete(user); }
	 */
}
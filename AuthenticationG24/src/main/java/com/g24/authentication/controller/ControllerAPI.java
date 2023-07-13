package com.g24.authentication.controller;

import com.g24.authentication.model.dto.UserDto;
import com.g24.authentication.model.entity.User;
import com.g24.authentication.model.mapper.MapperUserDto;
import com.g24.authentication.model.service.PasswordForgotService;
import com.g24.authentication.model.service.PasswordResetService;
import com.g24.authentication.model.service.RegisterService;
import com.g24.authentication.model.service.UserService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControllerAPI
{
	@Autowired private UserService userService;
	@Autowired private PasswordForgotService passwordForgotService;
	@Autowired private PasswordResetService passwordResetService;
	@Autowired private RegisterService registerService;
	@Autowired private MapperUserDto mapperUserDto;



	@GetMapping("/userid")
	public String getUserId(@RequestBody String jsonEmail) 
	{
		JSONObject obj = new JSONObject(jsonEmail);
		String email = obj.getString("email");

		User user = userService.findUserByEmail(email);
		if(user != null) {
			return user.getId().toString();
		}
		return "NULL";
	}

	@PostMapping("/verifyauth")
	public boolean verifyAuth(@RequestBody String credentials) 
	{
		JSONObject obj = new JSONObject(credentials);
		String email = obj.getString("email");
		String password = obj.getString("password");


		try{
			User user = userService.findUserByEmail(email);
			if(!user.isEnabled()) {return false;}

			return userService.matchPassword(user, password);   
		}
		catch(Exception e) {
			return false;
		}

		  
	}

	@PostMapping("/forgot-password")
	public boolean forgotPassword(@RequestBody String jsonEmail)
	{
		JSONObject obj = new JSONObject(jsonEmail);
		String email = obj.getString("email");
		try
		{
			passwordForgotService.generatePasswordResetToken(email);
			return true;
		}
		catch(Exception ex)
		{
			return false;

		}

	}

	@PostMapping ("/reset-password")
	public boolean resetPassword(@RequestBody String jsontoken)
	{
		JSONObject obj = new JSONObject(jsontoken);
		String token = obj.getString("token");
		String password = obj.getString("password");
		try
		{
			token = passwordResetService.findPasswordResetToken(token);
			passwordResetService.passwordReset(token, password);
			return true;
		}
		catch(Exception ex)
		{
			return false;

		}

	}

	@PostMapping ("/register")
	public boolean register(@RequestBody String jsonReg)
	{
		JSONObject obj = new JSONObject(jsonReg);
		UserDto userDto = new UserDto(null, obj.getString("firstName"),obj.getString("lastName"), obj.getString("email"),obj.getString("degreeCourse"),obj.getString("university"));
		String password = obj.getString("password");

		try
		{
			registerService.registerUser(mapperUserDto.toUser(userDto), password);
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}

	@PostMapping ("/confirm-registration")
	public boolean confirmRegistration(@RequestBody String jsonReg)
	{
		JSONObject obj = new JSONObject(jsonReg);
		String token = obj.getString("token");

		try
		{
			registerService.enableUser(token);
			return true;
		}
		catch(Exception ex)
		{
			System.out.print(ex.getMessage());
			return false;
		}
	}

}
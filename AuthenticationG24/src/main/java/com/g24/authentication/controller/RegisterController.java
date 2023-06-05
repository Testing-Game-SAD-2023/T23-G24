package com.g24.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.g24.authentication.dto.UserDto;
import com.g24.authentication.entity.User;
import com.g24.authentication.service.UserService;

import jakarta.validation.Valid;

@Controller
public class RegisterController
{
	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showRegistrationForm(@ModelAttribute("user") UserDto userDto)
	{
		return "register";
	}

	@PostMapping("/register/save")
	public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result)
	{

		User existingUser = userService.findUserByEmail(userDto.getEmail());

		if(!userDto.getEmail().equals( userDto.getConfirmEmail())) {
			result.rejectValue("confirmEmail", null, "The email fields must match");
			result.rejectValue("email", null, ""); }
		else if(existingUser != null ) {
			result.rejectValue("email", null,
					"C'è già un account registrato con la stessa email"); }



		if(!userDto.getPassword().equals(userDto.getConfirmPassword())) {
			result.rejectValue("confirmPassword", null,"The password fields must match");
			result.rejectValue("password", null, "");
		}


		if(result.hasErrors())
		{
			return "/register";
		}

		userService.saveUser(userDto);
		return "redirect:/login?registerSuccess";
	}
}
package com.g24.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g24.authentication.model.dto.UserDto;
import com.g24.authentication.model.dto.UserRegistrationDto;
import com.g24.authentication.model.mapper.MapperUserDto;
import com.g24.authentication.model.service.RegisterService;
import com.g24.authentication.utils.exceptions.TokenException;
import com.g24.authentication.utils.exceptions.UserException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class RegisterController
{
	@Autowired private RegisterService registerService;
	@Autowired private MapperUserDto mapperUserDto;

	@GetMapping("/register")
	public String showRegistrationForm(@ModelAttribute("user") UserDto userDto, @ModelAttribute("userRegistration") UserRegistrationDto userRegDto)
	{
		return "register";
	}

	@PostMapping("/register/save")
	public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult resultUser, 
			                   @Valid @ModelAttribute("userRegistration") UserRegistrationDto userRegDto, BindingResult result, Model model
			                   /*,HttpServletRequest request*/)
	{
		
		if(!userRegDto.getPassword().equals(userRegDto.getConfirmPassword())) {
			result.rejectValue("confirmPassword", null,"The password fields must match");
			result.rejectValue("password", null, "");
			
		}
		
		if(!userDto.getEmail().equals(userRegDto.getConfirmEmail())) {

			resultUser.rejectValue("email", null, "");
			result.rejectValue("confirmEmail", null, "The email fields must match");}

		if(resultUser.hasErrors() || result.hasErrors())
		{
			return "/register";
		}
		else
		{
			try
			{
				registerService.registerUser(mapperUserDto.toUser(userDto), userRegDto.getPassword()/*, request*/);			
			}
			catch(UserException userException)
			{
				resultUser.rejectValue("email", null, userException.getMessage());
				return "/register";
			}
			
			  catch (Exception ex ) {
				  model.addAttribute("error","An unexpected error has occurred.");
				  return "/register";
				  }
			 
		}

		return "redirect:/login?registerSuccess";
		
	}

	@GetMapping("/confirm-registration")
	public String confirmRegistrationPage(@RequestParam String token, Model model)
	{	
		try
		{
		    registerService.enableUser(token);
		}
		catch(TokenException tokenException)
		{
			model.addAttribute("error", tokenException.getMessage());
			return "confirm-registration";
		}
		catch (Exception ex )
		{
			model.addAttribute("error", "An unexpected error has occurred.");
			return "confirm-registration";
		}

		return "redirect:/login?registerComplete";
	}
}
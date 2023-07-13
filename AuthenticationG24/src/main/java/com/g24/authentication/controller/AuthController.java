package com.g24.authentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.g24.authentication.model.dto.UserDto;
import com.g24.authentication.model.mapper.MapperUserDto;
import com.g24.authentication.model.service.AuthService;
import com.g24.authentication.model.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthController
{
	@Autowired	private AuthService authService;
	@Autowired	private UserService userService;
	@Autowired  private MapperUserDto mapperUserDto;

	@GetMapping("/index")
	public String index()
	{
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		if(!authService.isUserAuthenticated())
			return "login";
		else
			return "redirect:/home";
	}

	@GetMapping("/home")
	public String home() 
	{
		if(authService.isAdmin())
			return "redirect:/home/admin";
		else
			return "redirect:/home/user";
	}

	@GetMapping("/home/user")
	public String homeUser(Model model)
	{
		UserDto user = mapperUserDto.toDto(authService.getUser());
		model.addAttribute("user", user);
		return "homeuser"; 
	}

	@GetMapping("home/admin")
	public String homeAdmin(Model model)
	{
		try {
			List<UserDto> users = userService.findAllUsers().stream().map(mapperUserDto::toDto).collect(Collectors.toList());
			model.addAttribute("users", users);
		}
		catch (Exception ex )
		{
			model.addAttribute("error", "An unexpected error has occurred.");
		}
		return "homeadmin";
	}
	
	@GetMapping("/userid")
	@ResponseBody
	public long getUserId()
	{
		return authService.getUserId(); 
	}
}
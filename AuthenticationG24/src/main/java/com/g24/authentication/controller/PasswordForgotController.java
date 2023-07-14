package com.g24.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import com.g24.authentication.model.dto.PasswordForgotDto;
import com.g24.authentication.model.service.PasswordForgotService;
import com.g24.authentication.utils.exceptions.TokenException;
import com.g24.authentication.utils.exceptions.UserException;


@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController
{
	@Autowired private PasswordForgotService passwordForgotService;


	@GetMapping
	public String displayForgotPasswordPage(@ModelAttribute("forgotPasswordForm") PasswordForgotDto form) {
		return "forgot-password";
	}

	@PostMapping
	public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
			BindingResult result, Model model) {

		if (result.hasErrors()){
			return "forgot-password";
		}
		else
		try
		{
			passwordForgotService.generatePasswordResetToken(form.getEmail());
			return "redirect:/forgot-password?success";
		}
		catch(TokenException tokenException)
		{
			model.addAttribute("error", tokenException.getMessage());
			return "forgot-password";
		}
		catch(UserException userException) {
			result.rejectValue("email", null, userException.getMessage());
			return "forgot-password";
		}
		catch (Exception ex) {
			model.addAttribute("error","An unexpected error has occurred.");
			return "forgot-password";
		}

		
	}

}

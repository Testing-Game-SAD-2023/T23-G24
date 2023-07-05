package com.g24.authentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;

import com.g24.authentication.model.dto.PasswordResetDto;
import com.g24.authentication.model.service.PasswordResetService;
import com.g24.authentication.utils.exceptions.PasswordException;
import com.g24.authentication.utils.exceptions.TokenException;

@Controller
@RequestMapping("/reset-password")
public class PasswordResetController
{	
	@Autowired private PasswordResetService passwordResetService;

	@ModelAttribute("passwordResetForm") public PasswordResetDto passwordResetAttr() {
		return new PasswordResetDto();
	}
	@GetMapping
	public String displayResetPasswordPage(@RequestParam String token, Model model)
	{	
		try
		{
			String resetToken = passwordResetService.findPasswordResetToken(token);
			model.addAttribute("token", resetToken);
		}
		catch(TokenException tokenException)
		{
			model.addAttribute("error", tokenException.getMessage());
		}
		catch (Exception ex )
		{
			model.addAttribute("error", "An unexpected error has occurred.");
		}
		
		return "reset-password";
	}

	@PostMapping
	
	public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDto form, BindingResult result,Model model, RedirectAttributes redirectAttributes)
	{	
		if(!form.getPassword().equals(form.getConfirmPassword())) 
		{
			result.rejectValue("confirmPassword", null,"The password fields must match");
			result.rejectValue("password", null, "");
		}
		else
		{
			try
			{
				passwordResetService.passwordReset(form.getToken(), form.getPassword());
			}
			catch(PasswordException passwordException)
			{
				result.rejectValue("password", null, passwordException.getMessage());
			}
			catch (Exception ex )
			{
				model.addAttribute("error", "An unexpected error has occurred.");
				redirectAttributes.addFlashAttribute("error", "An unexpected error has occurred.");
			}
		}
		
		if (result.hasErrors()||model.containsAttribute("error"))
		{
			redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
			redirectAttributes.addFlashAttribute("passwordResetForm", form);
			return  "redirect:/reset-password?token=" + form.getToken();
		} 
		

		return "redirect:/login?resetSuccess";
	}
}

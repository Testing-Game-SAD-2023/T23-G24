package com.g24.authentication.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
 
import jakarta.servlet.http.HttpServletRequest;
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
 
    	try
    	{
    		passwordForgotService.generatePasswordResetToken(form.getEmail());
    	}
    	catch(TokenException tokenException)
    	{
        	model.addAttribute("error", tokenException.getMessage());
        	
    	}
    	catch(UserException userException) {
    		result.rejectValue("email", null, userException.getMessage());
    	}
    	catch (Exception ex) {
    		model.addAttribute("error","An unexpected error has occurred.");
    	}
        
        if (result.hasErrors() || model.containsAttribute("error")){
            return "forgot-password";
        }
        
        return "redirect:/forgot-password?success";
    }
 
}
 
package com.g24.authentication.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserRegistrationDto
{

	private String confirmEmail;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z\s]).{8,}", message="Password should have at least eight characters, one lowercase letter, one uppercase letter and one number or special character")
	private String password;

	private String confirmPassword;

}
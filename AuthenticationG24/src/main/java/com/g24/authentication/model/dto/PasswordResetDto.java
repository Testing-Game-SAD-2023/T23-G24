package com.g24.authentication.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PasswordResetDto 
{
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z\s]).{8,}", message="Password should have at least eight characters, one lowercase letter, one uppercase letter and one number or special character")
	private String password;

	@NotEmpty(message = "First name should not be empty")
	private String confirmPassword;

	@NotEmpty
	private String token;
}

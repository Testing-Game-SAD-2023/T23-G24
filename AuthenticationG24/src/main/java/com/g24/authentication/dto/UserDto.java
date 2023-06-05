package com.g24.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto
{
	private Long id;
	@NotEmpty(message = "First name should not be empty")
	private String firstName;
	@NotEmpty(message = "Last name should not be empty")
	private String lastName;
	
	@NotEmpty(message = "Email should not be empty")
	@Email(message = "Email has an invalid format")
	private String email;

	private String confirmEmail;

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z\s]).{8,}", message="Password should have at least eight characters, one lowercase letter, one uppercase letter and one number or special character")
	private String password;

	private String confirmPassword;

	private String degreeCourse;

	private String university;
}
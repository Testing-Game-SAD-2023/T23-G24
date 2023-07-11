package com.g24.authentication.model.service;

import com.g24.authentication.model.entity.User;

public interface AuthService 
{
	boolean isAdmin();

	boolean isUserAuthenticated();

	long getUserId();

	User getUser();
}

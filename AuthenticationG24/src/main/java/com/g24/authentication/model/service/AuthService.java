package com.g24.authentication.model.service;

//import java.util.List;

//import org.springframework.security.core.Authentication;

//import com.g24.authentication.entity.User;

public interface AuthService 
{
	boolean isAdmin();
	
	boolean isUserAuthenticated();//Authentication authentication);
	
	//List<User> findAllUsers();
}

package com.g24.authentication.controller;

import com.g24.authentication.model.entity.User;
import com.g24.authentication.model.service.UserService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControllerAPI
{
	@Autowired private UserService userService;
	
    @GetMapping("/userid")
    public String getUserId(@RequestBody String jsonEmail) 
    {
        JSONObject obj = new JSONObject(jsonEmail);
        String email = obj.getString("email");
        
        User user = userService.findUserByEmail(email);
        if(user != null) {
            return user.getId().toString();
        }
        return "NULL";
    }
    
    @PostMapping("/verifyauth")
    public boolean verifyAuth(@RequestBody String credentials) 
    {
        JSONObject obj = new JSONObject(credentials);
        String email = obj.getString("email");
        String password = obj.getString("password");
       
        User user = new User();

        try{
           user = userService.findUserByEmail(email);
        }
        catch(Exception e) {
            return false;
        }
        
        if(!user.isEnabled()) {return false;}

        return userService.matchPassword(user, password);     
    }
}
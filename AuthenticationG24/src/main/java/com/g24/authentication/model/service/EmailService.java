package com.g24.authentication.model.service;
 
import java.util.Map;

//import com.g24.authentication.entity.Mail;
 
public interface EmailService
{
	
	void sendEmail(String type, String to, Map<String, Object> modelMail) throws RuntimeException;
	
	//void sendEmail(Mail mail) throws RuntimeException;
}
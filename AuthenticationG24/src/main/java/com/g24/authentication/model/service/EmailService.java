package com.g24.authentication.model.service;

import java.util.Map;

import com.g24.authentication.model.entity.Mail;

public interface EmailService
{
	Mail createEmail(String type, String to, Map<String, Object> modelMail);
	void sendEmail(Mail mail) throws RuntimeException;

}
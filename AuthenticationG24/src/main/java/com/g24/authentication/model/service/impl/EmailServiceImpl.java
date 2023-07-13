package com.g24.authentication.model.service.impl;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.g24.authentication.model.entity.Mail;
import com.g24.authentication.model.service.EmailService;


@Service
public class EmailServiceImpl implements EmailService
{
	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Value("${url}") private String url;
	@Value("${server.port}") private String port;
	@Value("${spring.mail.username}") private String fromEmail;

	@Override
	public Mail createEmail(String type, String to, Map<String, Object> modelMail)
	{
		Mail mail = new Mail();
		mail.setType(type);
		mail.setFrom(fromEmail);
		mail.setTo(to);

		String link = null;
		switch(mail.getType()) {
		case "Registration":
			link = "/confirm-registration?token=";
			mail.setSubject("Registration request");
			mail.setHtmlTemplate("email/reg-email-template");
			break;
		case "PasswordReset":
			link = "/reset-password?token=";
			mail.setSubject("Password Reset request");
			mail.setHtmlTemplate("email/email-template");
			break;
		}
		modelMail.put("resetUrl", url.concat(port).concat(link) + (String) modelMail.get("token"));
		mail.setModel(modelMail);

		return mail;
	}

	@Override
	public void sendEmail(Mail mail) throws RuntimeException
	{
		try 
		{
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

			Context context = new Context();
			context.setVariables(mail.getModel());
			String html = templateEngine.process(mail.getHtmlTemplate(), context);

			helper.setTo(mail.getTo());
			helper.setText(html, true);
			helper.setSubject(mail.getSubject());
			helper.setFrom(mail.getFrom());

			emailSender.send(message);
		} 
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

}
package io.troof.bigpi.emailsender.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import io.troof.bigpi.emailsender.service.EmailSenderService;

@Service
public class EmailSenderServiceImpl implements EmailSenderService{
	
	private final JavaMailSender mailSender;
	
	public EmailSenderServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendEmail(String to, String subject, String message) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("troof.test@gmail.com");
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		
		this.mailSender.send(simpleMailMessage);
	}
	
}

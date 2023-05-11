package io.troof.bigpi.emailsenderimplementation.service.impl;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import io.troof.bigpi.emailsenderimplementation.repository.EmailRepository;
import io.troof.bigpi.emailsenderimplementation.model.EmailMessage;
import io.troof.bigpi.emailsenderimplementation.service.EmailService;

/** Email sender. */
@Service
public class EmailServiceImpl implements EmailService {
  
  private JavaMailSenderImpl mailSender;
  
  @Autowired
  private EmailRepository repository;
  
  public EmailServiceImpl() {
    this.mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
	mailSender.setPort(587);
	Properties props = mailSender.getJavaMailProperties();
	props.put("mail.transport.protocol", "smtp");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.debug", "true");
  }

  @Override
  public void sendEmail(EmailMessage email) {
    
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    email.setFrom(mailSender.getUsername());
    simpleMailMessage.setFrom(email.getFrom());
    simpleMailMessage.setTo(email.getTo());
    if (email.getCc() != null && email.getCc() != "") {
      simpleMailMessage.setCc(email.getCc());
    }
    if (email.getBcc() != null && email.getBcc() != "") {
      simpleMailMessage.setBcc(email.getBcc());
    }
    simpleMailMessage.setSubject(email.getSubject());
    simpleMailMessage.setText(email.getMessage());
    
    repository.save(email);
    
    this.mailSender.send(simpleMailMessage);
  }
  
  public void setParameters(String email, String password) {
	  mailSender.setUsername(email);
	  mailSender.setPassword(password);
  }
  
  public EmailRepository getRepository() {
	  return repository;
  }
}

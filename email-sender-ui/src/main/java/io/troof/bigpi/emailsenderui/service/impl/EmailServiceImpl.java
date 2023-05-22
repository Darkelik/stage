package io.troof.bigpi.emailsenderui.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import io.troof.bigpi.emailsenderui.repository.EmailRepository;
import io.troof.bigpi.emailsenderui.resource.AutoEmail;
import io.troof.bigpi.emailsenderui.resource.EmailMessage;
import io.troof.bigpi.emailsenderui.resource.User;
import io.troof.bigpi.emailsenderui.service.EmailService;

/** Email sender. */
@Service
public class EmailServiceImpl implements EmailService {
  
  private JavaMailSenderImpl mailSender;
  
  @Autowired
  private EmailRepository repository;
  
  /** Constructor. */
  public EmailServiceImpl() {
    this.mailSender = new JavaMailSenderImpl();
  }
  
  public void initValues(User user) {
	  mailSender.setHost(user.getHost());
	  mailSender.setPort(user.getPort());
	  mailSender.setUsername(user.getEmail());
	  mailSender.setPassword(user.getPassword());
	  Properties props = mailSender.getJavaMailProperties();
	  props.put("mail.transport.protocol", user.getProtocol());
	  props.put("mail.smtp.auth", user.getAuth());
	  props.put("mail.smtp.starttls.enable", user.getStartTls());
	  props.put("mail.debug", user.getDebug());
  }

  @Override
  public void sendEmail(EmailMessage email) {
    
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    email.setFrom(mailSender.getUsername());
    simpleMailMessage.setFrom(email.getFrom());
    simpleMailMessage.setTo(email.getTo().split(","));
    if (email.getCc() != null && email.getCc() != "") {
      simpleMailMessage.setCc(email.getCc().split(","));
    }
    if (email.getBcc() != null && email.getBcc() != "") {
      simpleMailMessage.setBcc(email.getBcc().split(","));
    }
    simpleMailMessage.setSubject(email.getSubject());
    simpleMailMessage.setText(email.getMessage());
    
    this.mailSender.send(simpleMailMessage);
  }
  
  public EmailRepository getRepository() {
    return repository;
  }
  
  public void setRepository(EmailRepository repository) {
    this.repository = repository;
  }

  public JavaMailSenderImpl getMailSender() {
    return mailSender;
  }

  public void setMailSender(JavaMailSenderImpl mailSender) {
    this.mailSender = mailSender;
  }
  
  public List<AutoEmail> getAllEmails() {
    return repository.findAll();
  }
  
  public Optional<AutoEmail> getEmailById(String id) {
    return repository.findById(id);
  }

  public void deleteAutoEmail(String id) {
    repository.deleteById(id);
  }
  
  public void saveAutoEmail(AutoEmail email) {
	  repository.save(email);
  }
  
  public void sendAutoEmail(AutoEmail email) {
	  sendEmail(email.getMessage());
  }
}

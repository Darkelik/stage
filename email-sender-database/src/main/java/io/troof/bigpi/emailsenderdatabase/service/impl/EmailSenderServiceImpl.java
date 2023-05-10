package io.troof.bigpi.emailsenderdatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import io.troof.bigpi.emailsenderdatabase.repository.EmailRepository;
import io.troof.bigpi.emailsenderdatabase.resource.EmailMessage;
import io.troof.bigpi.emailsenderdatabase.service.EmailSenderService;

/** Email sender. */
@Service
public class EmailSenderServiceImpl implements EmailSenderService {
  
  private final JavaMailSender mailSender;
  
  @Autowired
  private EmailRepository repository;
  
  @Autowired
  private Environment env;
  
  public EmailSenderServiceImpl(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void sendEmail(EmailMessage email) {
    
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    String from = env.getProperty("spring.mail.username");
    email.setFrom(from);
    simpleMailMessage.setFrom(from);
    simpleMailMessage.setTo(email.getTo());
    if (email.getCc() != null && email.getCc() != "") {
      simpleMailMessage.setCc(email.getCc());
    }
    if (email.getBcc() != null && email.getBcc() != "") {
      simpleMailMessage.setBcc(email.getBcc());
    }
    simpleMailMessage.setSubject(email.getSubject());
    simpleMailMessage.setText(email.getSubject());
    
    repository.save(email);
    
    this.mailSender.send(simpleMailMessage);
  }
  
  public List<EmailMessage> listAll(){
		return repository.findAll();
	}
  
  public void delete(long id) {
		repository.deleteById(id);
	}
}

package io.troof.bigpi.emailsender.service.impl;

import io.troof.bigpi.emailsender.service.EmailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/** Envoi des emails. */
@Service
public class EmailSenderServiceImpl implements EmailSenderService {
  
  private final JavaMailSender mailSender;
  
  public EmailSenderServiceImpl(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void sendEmail(String to, String cc, String bcc, String subject, String message) {
    
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(to);
    if (cc != null && cc != "") {
      simpleMailMessage.setCc(cc);
    }
    if (bcc != null && bcc != "") {
      simpleMailMessage.setBcc(bcc);
    }
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(message);
    
    this.mailSender.send(simpleMailMessage);
  }
}

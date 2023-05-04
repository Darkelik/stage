package io.troof.bigpi.emailsender.service.impl;

import io.troof.bigpi.emailsender.service.EmailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/** Email sender. */
@Service
public class EmailSenderServiceImpl implements EmailSenderService {
  
  private final JavaMailSender mailSender;
  
  public EmailSenderServiceImpl(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void sendEmail(String to, String cc, String bcc, String sub, String mes, String log) {
    
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom(log);
    simpleMailMessage.setTo(to);
    if (cc != null && cc != "") {
      simpleMailMessage.setCc(cc);
    }
    if (bcc != null && bcc != "") {
      simpleMailMessage.setBcc(bcc);
    }
    simpleMailMessage.setSubject(sub);
    simpleMailMessage.setText(mes);
    
    this.mailSender.send(simpleMailMessage);
  }
}

package io.troof.bigpi.emailsenderui.service.impl;

import io.troof.bigpi.emailsenderui.repository.EmailRepository;
import io.troof.bigpi.emailsenderui.resource.AutoEmail;
import io.troof.bigpi.emailsenderui.resource.EmailMessage;
import io.troof.bigpi.emailsenderui.resource.User;
import io.troof.bigpi.emailsenderui.service.EmailService;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

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
  
  /** Values initializer. */
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
  public void sendEmail(EmailMessage email, String from) {
    
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
    
    try {
      helper.setFrom(from);
      helper.setTo(email.getTo());

      String cc = email.getCc();
      if (cc != null && cc.length() > 0) {
        helper.setCc(cc.split(","));
      }
    
      String bcc = email.getBcc();
      if (bcc != null && bcc.length() > 0) {
        helper.setBcc(bcc.split(","));
      }
      
      helper.setSubject(email.getSubject());
      helper.setText(email.getMessage(), true);
      
      this.mailSender.send(mimeMessage);
        
    } catch (MessagingException e) {
      e.printStackTrace();
    }
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
  
  /** Sending a prepared e-mail. */
  public void sendAutoEmail(AutoEmail email, String from) {
    EmailMessage mail = new EmailMessage(email.getTo(), email.getCc(),
        email.getBcc(), email.getSubject(), email.getMessage());
    sendEmail(mail, from);
  }
}

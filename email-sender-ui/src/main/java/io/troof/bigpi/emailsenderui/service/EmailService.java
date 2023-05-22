package io.troof.bigpi.emailsenderui.service;

import java.util.List;
import java.util.Optional;

import io.troof.bigpi.emailsenderui.resource.AutoEmail;
import io.troof.bigpi.emailsenderui.resource.EmailMessage;

/** Email sender interface. */
public interface EmailService {
  public void sendEmail(EmailMessage email, String from);
  
  public List<AutoEmail> getAllEmails();
  
  public Optional<AutoEmail> getEmailById(String id);
  
  public void deleteAutoEmail(String id);
  
  public void sendAutoEmail (AutoEmail email, String from);
}

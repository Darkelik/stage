package io.troof.bigpi.emailsenderimplementation.service;

import io.troof.bigpi.emailsenderimplementation.model.EmailMessage;
import java.util.List;
import java.util.Optional;

/** Email sender interface. */
public interface EmailService {
  public void sendEmail(EmailMessage email);
  
  public List<EmailMessage> getAllEmails();
  
  public Optional<EmailMessage> getEmailById(long l);
  
  public void deleteEmail(long l);
}

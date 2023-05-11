package io.troof.bigpi.emailsenderimplementation.service;

import java.util.List;
import java.util.Optional;

import io.troof.bigpi.emailsenderimplementation.model.EmailMessage;

/** Email sender interface. */
public interface EmailService {
  public void sendEmail(EmailMessage email);
  public List<EmailMessage> getAllEmails();
  public Optional<EmailMessage> getEmailById(long l);
  public void deleteEmail(long l);
}

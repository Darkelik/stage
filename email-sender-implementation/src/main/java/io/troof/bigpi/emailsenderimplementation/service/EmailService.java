package io.troof.bigpi.emailsenderimplementation.service;

import io.troof.bigpi.emailsenderimplementation.model.EmailMessage;

/** Email sender interface. */
public interface EmailService {
  public void sendEmail(EmailMessage email);
}

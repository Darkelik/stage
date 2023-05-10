package io.troof.bigpi.emailsenderdatabase.service;

import io.troof.bigpi.emailsenderdatabase.resource.EmailMessage;

/** Email sender interface. */
public interface EmailSenderService {
  public void sendEmail(EmailMessage email);
}

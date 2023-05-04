package io.troof.bigpi.emailsender.service;

/** Email sender interface. */
public interface EmailSenderService {
  public void sendEmail(String to, String cc, String bcc, String sub, String mes, String log);
}

package io.troof.bigpi.emailsender.service;

/** Interface de l'envoi des emails. */
public interface EmailSenderService {
  public void sendEmail(String to, String cc, String bcc, String subject, String message);
}

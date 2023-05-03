package io.troof.bigpi.emailsender.service;

public interface EmailSenderService {
	public void sendEmail(String to, String cc, String bcc, String subject, String message);
}

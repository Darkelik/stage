package io.troof.bigpi.emailsenderui.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import io.troof.bigpi.emailsenderui.repository.EmailRepository;
import io.troof.bigpi.emailsenderui.resource.EmailMessage;

/** Service class tests. */
public class EmailServiceImplTests {

  private EmailServiceImpl emailService;
  private EmailRepository emailRepositoryMock;

  /** Initializer for all tests. */
  @BeforeEach
  public void setUp() {
    emailRepositoryMock = mock(EmailRepository.class);
    emailService = new EmailServiceImpl();
    emailService.setRepository(emailRepositoryMock);
    emailService.setParameters("fredericvaz2016@gmail.com", "aprjqviwqydnurgc");
  }

  @Test
  public void testSendEmail() {
    String to = "troof.test@gmail.com";
    String cc = "fredericvaz2016@gmail.com";
    String bcc = "troof.test@gmail.com";
    String subject = "Test Subject";
    String message = "Test message body";
    EmailMessage email = new EmailMessage(to, cc, bcc, subject, message);
    emailService.sendEmail(email);
    ArgumentCaptor<EmailMessage> emailCaptor = ArgumentCaptor.forClass(EmailMessage.class);
    verify(emailRepositoryMock).save(emailCaptor.capture());
    EmailMessage capturedEmail = emailCaptor.getValue();

    assertEquals(to, capturedEmail.getTo());
    assertEquals(cc, capturedEmail.getCc());
    assertEquals(bcc, capturedEmail.getBcc());
    assertEquals(subject, capturedEmail.getSubject());
    assertEquals(message, capturedEmail.getMessage());
    assertTrue(capturedEmail.getFrom().contains("fredericvaz2016@gmail.com"));
  }
    
  @Test
  public void testSetParameters() {
    emailService.setParameters("example2@gmail.com", "password2");

    assertEquals("example2@gmail.com", emailService.getMailSender().getUsername());
    assertEquals("password2", emailService.getMailSender().getPassword());
  }
}
package io.troof.bigpi.emailsender.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.troof.bigpi.emailsender.resource.EmailMessage;
import io.troof.bigpi.emailsender.service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class EmailControllerTest {

  @Mock
  private EmailSenderService emailSenderService;

  private EmailController emailController;

  @SuppressWarnings("deprecation")
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    emailController = new EmailController(emailSenderService);
  }

  @Test
  void testSendEmail() {
    String to = "test@example.com";
    String cc = "testcc@example.com";
    String bcc = "testbcc@example.com";
    String subject = "Test Subject";
    String message = "Test Message";
    EmailMessage emailMessage = new EmailMessage(to, cc, bcc, subject, message);

    ResponseEntity<String> response = emailController.sendEmail(emailMessage);

    verify(emailSenderService, times(1)).sendEmail(to, cc, bcc, subject, message);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Success sending", response.getBody());
  }
}

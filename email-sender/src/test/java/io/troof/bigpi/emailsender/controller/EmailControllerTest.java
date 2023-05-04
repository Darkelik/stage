package io.troof.bigpi.emailsender.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.troof.bigpi.emailsender.resource.EmailMessage;
import io.troof.bigpi.emailsender.service.EmailSenderService;
import org.junit.jupiter.api.BeforeEach;
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
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    emailController = new EmailController(emailSenderService);
  }
  
  @Test
  void testSendEmailInvalidEmail() {
    String to = "invalid-email";
    String cc = "testcc@example.com";
    String bcc = "testbcc@example.com";
    String subject = "Test Subject";
    String message = "Test Message";
    EmailMessage emailMessage = new EmailMessage(to, cc, bcc, subject, message);

    ResponseEntity<String> response = emailController.sendEmail(emailMessage);

    verify(emailSenderService, times(0)).sendEmail(to, cc, bcc, subject, message);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Invalid email address", response.getBody());
  }
  
  @Test
  void testSendEmailEmptyEmail() {
    String to = "";
    String cc = "testcc@example.com";
    String bcc = "testbcc@example.com";
    String subject = "";
    String message = "Test Message";
    EmailMessage emailMessage = new EmailMessage(to, cc, bcc, subject, message);

    ResponseEntity<String> response = emailController.sendEmail(emailMessage);

    verify(emailSenderService, times(0)).sendEmail(to, cc, bcc, subject, message);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("'To' cannot be empty", response.getBody());
  }

  @Test
  void testSendEmailEmptySubject() {
    String to = "test@example.com";
    String cc = "testcc@example.com";
    String bcc = "testbcc@example.com";
    String subject = "";
    String message = "Test Message";
    EmailMessage emailMessage = new EmailMessage(to, cc, bcc, subject, message);

    ResponseEntity<String> response = emailController.sendEmail(emailMessage);

    verify(emailSenderService, times(0)).sendEmail(to, cc, bcc, subject, message);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("'Subject' cannot be empty", response.getBody());
  }

  @Test
  void testSendEmailEmptyMessage() {
    String to = "test@example.com";
    String cc = "testcc@example.com";
    String bcc = "testbcc@example.com";
    String subject = "Test Subject";
    String message = "";
    EmailMessage emailMessage = new EmailMessage(to, cc, bcc, subject, message);

    ResponseEntity<String> response = emailController.sendEmail(emailMessage);

    verify(emailSenderService, times(0)).sendEmail(to, cc, bcc, subject, message);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("'Message' cannot be empty", response.getBody());
  }

  @Test
  void testSendEmailServiceError() {
    String to = "test@example.com";
    String cc = "testcc@example.com";
    String bcc = "testbcc@example.com";
    String subject = "Test Subject";
    String message = "Test Message";
    EmailMessage emailMessage = new EmailMessage(to, cc, bcc, subject, message);

    doThrow(new RuntimeException("Unable to send email"))
      .when(emailSenderService)
        .sendEmail(to, cc, bcc, subject, message);

    ResponseEntity<String> response = emailController.sendEmail(emailMessage);

    verify(emailSenderService, times(1)).sendEmail(to, cc, bcc, subject, message);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    assertEquals("Error sending email", response.getBody());
  }

  @Test
  void testSendEmailWithCcAndBcc() {
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

  @Test
  void testSendEmailWithoutCcAndBcc() {
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

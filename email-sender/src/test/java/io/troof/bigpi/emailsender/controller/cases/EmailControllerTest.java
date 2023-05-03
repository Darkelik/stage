package io.troof.bigpi.emailsender.controller.cases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.troof.bigpi.emailsender.controller.EmailController;
import io.troof.bigpi.emailsender.resource.EmailMessage;
import io.troof.bigpi.emailsender.service.EmailSenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** Tests de cas. */
public class EmailControllerTest {

  @Mock
  private EmailSenderService emailSenderService;

  private EmailController emailController;

  @SuppressWarnings("deprecation")
  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    emailController = new EmailController(emailSenderService);
  }

  @Test
  public void testSendEmail() {
    String to = "test1@test.com";
    String cc = "test2@test.com";
    String bcc = "test3@test.com";
    String subject = "Test email";
    String message = "This is a test email";

    EmailMessage emailMessage = new EmailMessage(to, cc, bcc, subject, message);

    ResponseEntity<String> response = emailController.sendEmail(emailMessage);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Success sending", response.getBody());
  }

  @Test
  public void testGetEmails() {
    ResponseEntity<EmailSenderService> response = emailController.getEmails();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(emailSenderService, response.getBody());
  }
}

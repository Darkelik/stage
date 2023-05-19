package io.troof.bigpi.emailsenderui.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.troof.bigpi.emailsenderui.repository.EmailRepository;
import io.troof.bigpi.emailsenderui.resource.Connection;
import io.troof.bigpi.emailsenderui.resource.EmailMessage;
import io.troof.bigpi.emailsenderui.service.impl.EmailServiceImpl;

/** Controller class tests. */
public class EmailControllerTests {
    
  private EmailServiceImpl emailService;
  private EmailRepository emailRepositoryMock;
  private EmailController emailController;
  
  /** Initializer for all tests. */
  @BeforeEach
  public void setUp() {
    emailRepositoryMock = mock(EmailRepository.class);
    emailService = new EmailServiceImpl();
    emailService.setRepository(emailRepositoryMock);
    emailService.setParameters("fredericvaz2016@gmail.com", "aprjqviwqydnurgc");
    emailController = new EmailController();
    emailController.setService(emailService);
  }
    
  @Test
  public void testSendEmail() {
    EmailMessage emailMessage = new EmailMessage("trof.test@gmail.com", null, null, "Sub", "Mes");
    ResponseEntity<String> responseEntity = emailController.sendEmail(emailMessage);
    
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }
    
  @Test
  public void testGetAllEmails() {
    EmailMessage message1 = new EmailMessage("trof.test@gmail.com", null, null, "Sub 1", "Mes 1");
    EmailMessage message2 = new EmailMessage("trof.test@gmail.com", null, null, "Sub 2", "Mes 2");
    List<EmailMessage> emails = new ArrayList<>();
    emails.add(message1);
    emails.add(message2);
    when(emailService.getAllEmails()).thenReturn(emails);
    ResponseEntity<List<EmailMessage>> responseEntity = emailController.getAllEmails();
    
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(2, responseEntity.getBody().size());
    assertEquals(message1, responseEntity.getBody().get(0));
    assertEquals(message2, responseEntity.getBody().get(1));
  }
    
  @Test
  public void testGetEmailById() throws Exception {
    long emailId = 1L;
    EmailMessage email = new EmailMessage();
    email.setId(emailId);
    when(emailService.getEmailById(emailId)).thenReturn(Optional.of(email));
    ResponseEntity<?> response = emailController.getEmailById(emailId);
    
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(email, response.getBody());
  }

  @Test
  public void testGetEmailByIdNotFound() throws Exception {
    long emailId = 1L;
    when(emailService.getEmailById(emailId)).thenReturn(Optional.empty());
    ResponseEntity<?> response = emailController.getEmailById(emailId);
    
    assertEquals("email n°" + emailId + " doesn't exist.", response.getBody());
  }

  @Test
  public void testDeleteEmail() throws Exception {
    long emailId = 1L;
    EmailMessage email = new EmailMessage();
    email.setId(emailId);
    when(emailService.getEmailById(emailId)).thenReturn(Optional.of(email));
    ResponseEntity<String> response = emailController.deleteEmail(emailId);
    
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("email n°" + emailId + " deleted successfully.", response.getBody());
  }

  @Test
  public void testDeleteEmailNotFound() throws Exception {
    long emailId = 1L;
    when(emailService.getEmailById(emailId)).thenReturn(Optional.empty());
    ResponseEntity<String> response = emailController.deleteEmail(emailId);
    
    assertEquals("email n°" + emailId + " doesn't exist.", response.getBody());
  }
    
  @Test
  void testConnect() {
    Connection connection = new Connection("test@example.com", "password");
    ResponseEntity<?> response = emailController.connect(connection);
    
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("using " + connection.getEmail() + " with "
            + connection.getPassword() + ".\nMake sure this information is correct.",
            response.getBody());
  }
    
}

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
import io.troof.bigpi.emailsenderui.repository.UserRepository;
import io.troof.bigpi.emailsenderui.resource.AutoEmail;
import io.troof.bigpi.emailsenderui.resource.Connection;
import io.troof.bigpi.emailsenderui.resource.EmailMessage;
import io.troof.bigpi.emailsenderui.resource.SmallConnection;
import io.troof.bigpi.emailsenderui.resource.User;
import io.troof.bigpi.emailsenderui.service.impl.EmailServiceImpl;
import io.troof.bigpi.emailsenderui.service.impl.UserServiceImpl;

/** Controller class tests. */
public class EmailControllerTests {
    
  private EmailServiceImpl emailService;
  private EmailRepository emailRepositoryMock;
  private UserServiceImpl userService;
  private UserRepository userRepositoryMock;
  private EmailController emailController;
  
  /** Initializer for all tests. */
  @BeforeEach
  public void setUp() {
    emailRepositoryMock = mock(EmailRepository.class);
    emailService = new EmailServiceImpl();
    emailService.setRepository(emailRepositoryMock);
    
    userRepositoryMock = mock(UserRepository.class);
    userService = new UserServiceImpl();
    userService.setRepository(userRepositoryMock);
    
    Connection co= new Connection("Frédéric Vaz", "fredericvaz2016@gmail.com", "aprjqviwqydnurgc", "smtp.gmail.com", 587, "smtp", "true", "true", "true");
    User user = new User(co);
    user.setId(1);
    userService.register(user);
    userService.setConnectedUser(user);
    
    emailService.initValues(user);
    
    emailController = new EmailController();
    emailController.setEmailService(emailService);
    emailController.setUserService(userService);
  }
  
  @Test
  public void testGetAllEmails() {
    AutoEmail message1 = new AutoEmail("test1", "trof.test@gmail.com", null, null, "Sub 1", "Mes 1");
    AutoEmail message2 = new AutoEmail("test2", "trof.test@gmail.com", null, null, "Sub 2", "Mes 2");
    List<AutoEmail> emails = new ArrayList<>();
    emails.add(message1);
    emails.add(message2);
    when(emailService.getAllEmails()).thenReturn(emails);
    ResponseEntity<List<AutoEmail>> responseEntity = emailController.getAllEmails();
    
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(2, responseEntity.getBody().size());
    assertEquals(message1, responseEntity.getBody().get(0));
    assertEquals(message2, responseEntity.getBody().get(1));
  }
  
  @Test
  public void testGetEmailById() {
    String emailId = "test";
    AutoEmail email = new AutoEmail();
    email.setId(emailId);
    when(emailService.getEmailById(emailId)).thenReturn(Optional.of(email));
    ResponseEntity<?> response = emailController.getEmailById(emailId);
    
    assertEquals(email, response.getBody());
    
    
    ResponseEntity<?> response2 = emailController.getEmailById("fail");
    
    assertEquals("Auto email with id fail doesn't exist.", response2.getBody());
  }
  
  @Test
  public void testDeleteAutoEmail() {
    String emailId = "test";
    AutoEmail email = new AutoEmail();
    email.setId(emailId);
    when(emailService.getEmailById(emailId)).thenReturn(Optional.of(email));
    ResponseEntity<String> response = emailController.deleteAutoEmail(emailId);
    
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Auto email with id " + emailId + " deleted successfully.", response.getBody());
  
    
    String emailId2 = "fail";
    when(emailService.getEmailById(emailId2)).thenReturn(Optional.empty());
    ResponseEntity<String> response2 = emailController.deleteAutoEmail(emailId2);
    
    assertEquals("Auto email with id " + emailId2 + " doesn't exist.", response2.getBody());
  }
    
  @Test
  public void testRegister() {
	  Connection co = new Connection("test","test@at.com","pass","at.com",85,"smtp","true","true","true");
	  ResponseEntity<String> response = emailController.register(co);
	  assertEquals("User " + co.getEmail() + " successfully registered.", response.getBody());
  }
  
  @Test
  void testConnect() {
	SmallConnection sco = new SmallConnection("fredericvaz2016@gmail.com","aprjqviwqydnurgc");
    ResponseEntity<String> response3 = emailController.connect(sco);
		
	assertEquals(sco.getEmail() + " is already connected.",response3.getBody());    
  }
  
  @Test
  public void testDisconnect() {
	  ResponseEntity<String> response = emailController.disconnect();
	  
	  assertEquals("fredericvaz2016@gmail.com successfully disconnected.", response.getBody());
  }
  
  @Test
  public void testSendEmail() {
    EmailMessage emailMessage = new EmailMessage("troof.test@gmail.com", null, null, "Sub", "Mes");
    ResponseEntity<String> responseEntity = emailController.sendEmail(emailMessage);
    
    assertEquals("Email successfully sent.", responseEntity.getBody());
  }
    
  @Test
  public void testPrepareAutoEmail() {
	  AutoEmail email = new AutoEmail("test","troof.test@gmail.com","","","subject","message");
	  ResponseEntity<String> response = emailController.prepareAutoEmail(email);
      assertEquals("Auto email with id " + email.getId() + " successfully created.", response.getBody());
  }
  
  @Test
  public void testSendAutoEmail() {    
    @SuppressWarnings("unchecked")
	ResponseEntity<String> responseEntity = (ResponseEntity<String>) emailController.sendAutoEmail("test");
    
    assertEquals("Auto email with id test doesn't exist.", responseEntity.getBody());
    
  }
    
}

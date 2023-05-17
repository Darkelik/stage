package io.troof.bigpi.emailsenderimplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.troof.bigpi.emailsenderimplementation.controller.EmailController;
import io.troof.bigpi.emailsenderimplementation.model.Connection;
import io.troof.bigpi.emailsenderimplementation.model.EmailMessage;
import io.troof.bigpi.emailsenderimplementation.repository.EmailRepository;
import io.troof.bigpi.emailsenderimplementation.service.impl.EmailServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest
class EmailSenderImplementationApplicationTests {

  private EmailServiceImpl emailService;
  @Mock
  private EmailRepository emailRepositoryMock;
  private EmailController emailController;
  
  /** Initializer for all tests. */
  @BeforeEach
  public void setUp() {
    emailRepositoryMock = mock(EmailRepository.class);
    emailService = new EmailServiceImpl();
    emailService.setRepository(emailRepositoryMock);
    emailController = new EmailController();
    emailController.setService(emailService);
  }
  
  /** The test case, testing all functions at once. */
  @Test
  public void applicationTestCase() {
    // In order to use this application, an external application is required.
    // In my case, I used Postman, but any other application compatible with SpringBoot is fine.

    // TEST 1 : Connecting a user. 
    // The user must provide his email address and a valid application password in JSON format.
  
    /* example :

       {
           "email" : "fredericvaz2016@gmail.com",
           "password" : "aprjqviwqydnurgc"
       }
    
    */

    // Those aren't checked if valid, so the app won't work if wrong information was provided.
    // The user's information isn't stored in the database.
    // The user need to provide his connection information each time the application is restarted.

    // Connect at localhost:8080/api/emailsender/connect, in PUT mode.

    Connection connection = new Connection("fredericvaz2016@gmail.com", "aprjqviwqydnurgc");
    ResponseEntity<String> response1 = emailController.connect(connection);
    
    assertEquals(HttpStatus.OK, response1.getStatusCode());
    assertEquals("using " + connection.getEmail() + " with "
            + connection.getPassword() + ".\nMake sure this information is correct.",
            response1.getBody());
    
    // TEST 2 : Sending some e-mails.
    // The emailMessage requires "to", "cc", "bcc", "subject" and "message" fields.
    // "cc" and "bcc" fields can be left empty, but can only contain one email address.
    
    /* example :

    {
        "to" : "troof.test@gmail.com",
        "cc" : "fredericvaz2016@gmail.com",
        "bcc" : "",
        "subject" : "Test subject",
        "message" : "Test message"
    }
 
    */
    
    // "id" and "from" are automatically set.
    // Those e-mails will be stored in database, keeping track of what the user sent.
    // All e-mails are removed from database when the application is restarted.
    
    // Send an e-mail at localhost:8080/api/emailsender/send, in POST mode.
    
    // e-mail without cc and bcc.
    EmailMessage message1 = new EmailMessage(
            "trof.test@gmail.com",
            "",
            "",
            "Subject",
            "Message"
        );
    
    ResponseEntity<String> response2; 
    
    response2 = emailController.sendEmail(message1);
    assertEquals(HttpStatus.OK, response2.getStatusCode());
    verify(emailRepositoryMock).save(message1);
    
    // e-mail without bcc.
    EmailMessage message2 = new EmailMessage(
            "trof.test@gmail.com",
            "fredericvaz2016@gmail.com",
            "",
            "Subject",
            "Message"
        );
    
    response2 = emailController.sendEmail(message2);
    assertEquals(HttpStatus.OK, response2.getStatusCode());
    verify(emailRepositoryMock).save(message2);
    
    // full e-mail.
    EmailMessage message3 = new EmailMessage(
            "trof.test@gmail.com", 
            "fredericvaz2016@gmail.com", 
            "frederic.vaz@universite-paris-saclay.fr", 
            "Subject",
            "Message"
        );
    
    response2 = emailController.sendEmail(message3);
    assertEquals(HttpStatus.OK, response2.getStatusCode());
    verify(emailRepositoryMock).save(message3);
    
    // TEST 3 : Check all e-mails sent with the application.
    // This function is used to check all e-mails sent with the app since its last restart.
    
    // Check all e-mails at localhost:8080/api/emailsender/emails, in GET mode.
    
    // Unfortunately, e-mails aren't correctly sent in database during testing.
    // To test this function, I need to add them manually to a list.
    List<EmailMessage> emails = new ArrayList<>();
    
    emails.add(message1);
    emails.add(message2);
    emails.add(message3);
    when(emailService.getAllEmails()).thenReturn(emails);
    ResponseEntity<List<EmailMessage>> response3 = emailController.getAllEmails();
    
    assertEquals(HttpStatus.OK, response3.getStatusCode());
    assertEquals(3, response3.getBody().size());
    assertEquals(message1, response3.getBody().get(0));
    assertEquals(message2, response3.getBody().get(1));
    assertEquals(message3, response3.getBody().get(2));
    verify(emailRepositoryMock).findAll();
    
    // TEST 4 : Check a specific e-mail sent with the application.
    // This function is used to check the details of a specified e-mail id.
    
    // Check a specific e-mail at localhost:8080/api/emailsender/emails/{id}, in GET mode.
    
    // Same as before, this test requires the list.
    when(emailService.getEmailById(2L)).thenReturn(Optional.of(message2));
    ResponseEntity<?> response4 = emailController.getEmailById(2L);
    
    assertEquals(HttpStatus.OK, response4.getStatusCode());
    assertEquals(message2, response4.getBody());
    verify(emailRepositoryMock).findById(2L);
    
    // TEST 5 : Check a specific e-mail, but the id doesn't exist.
    // This is the same test as the last one, except we use an inexistent id.
    
    // Same as before, this test requires the list.
    when(emailService.getEmailById(4L)).thenReturn(Optional.empty());
    ResponseEntity<?> response5 = emailController.getEmailById(4L);
    
    assertEquals(HttpStatus.BAD_REQUEST, response5.getStatusCode());
    assertEquals("email n°4 doesn't exist.", response5.getBody());
    
    // TEST 6 : Delete a specific e-mail from the database.
    // This function is used to remove a specific e-mail from the database.
    // Note that it won't unsend the e-mail though.
    
    // Delete a specific e-mail at localhost:8080/api/emailsender/emails/{id}, in DELETE mode.
    
    // Same as before, this test requires the list.
    when(emailService.getEmailById(1L)).thenReturn(Optional.of(message1));
    ResponseEntity<String> response6 = emailController.deleteEmail(1L);
    
    assertEquals(HttpStatus.OK, response6.getStatusCode());
    assertEquals("email n°1 deleted successfully.", response6.getBody());
    verify(emailRepositoryMock).deleteById(1L);
    
    // TEST 7 : Delete a specific e-mail, but the id doesn't exist.
    // This is the same test as the last one, except we use an inexistent id.
    
    // Same as before, this test requires the list.
    when(emailService.getEmailById(5L)).thenReturn(Optional.empty());
    ResponseEntity<String> response7 = emailController.deleteEmail(5L);
    
    assertEquals(HttpStatus.BAD_REQUEST, response7.getStatusCode());
    assertEquals("email n°5 doesn't exist.", response7.getBody());
    
  }
}

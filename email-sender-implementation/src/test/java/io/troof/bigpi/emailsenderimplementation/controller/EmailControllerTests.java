package io.troof.bigpi.emailsenderimplementation.controller;

import io.troof.bigpi.emailsenderimplementation.model.Connection;
import io.troof.bigpi.emailsenderimplementation.model.EmailMessage;
import io.troof.bigpi.emailsenderimplementation.repository.EmailRepository;
import io.troof.bigpi.emailsenderimplementation.service.impl.EmailServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmailControllerTests {
    
	private EmailServiceImpl emailService;
    private EmailRepository emailRepositoryMock;
    private EmailController emailController;
    
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
        EmailMessage emailMessage = new EmailMessage("trof.test@gmail.com", null, null, "Test Subject", "Test Message");
        ResponseEntity<EmailMessage> responseEntity = emailController.sendEmail(emailMessage);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void testGetAllEmails() {
        EmailMessage emailMessage1 = new EmailMessage("trof.test@gmail.com", null, null, "Test Subject 1", "Test Message 1");
        EmailMessage emailMessage2 = new EmailMessage("trof.test@gmail.com", null, null, "Test Subject 2", "Test Message 2");
        List<EmailMessage> emails = new ArrayList<>();
        emails.add(emailMessage1);
        emails.add(emailMessage2);
        when(emailService.getAllEmails()).thenReturn(emails);
        ResponseEntity<List<EmailMessage>> responseEntity = emailController.getAllEmails();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        assertEquals(emailMessage1, responseEntity.getBody().get(0));
        assertEquals(emailMessage2, responseEntity.getBody().get(1));
    }
    
    @Test
    public void testGetEmailById() throws Exception {
        
        long emailId = 1L;
        EmailMessage email = new EmailMessage();
        email.setId(emailId);
        when(emailService.getEmailById(emailId)).thenReturn(Optional.of(email));
        
        ResponseEntity<EmailMessage> response = emailController.getEmailById(emailId);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(email, response.getBody());
    }

    @Test
    public void testGetEmailByIdNotFound() throws Exception {
       
        long emailId = 1L;
        when(emailService.getEmailById(emailId)).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(Exception.class, () -> {
            emailController.getEmailById(emailId);
        });
        assertEquals("Not Found", exception.getMessage());
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
        
        Exception exception = assertThrows(Exception.class, () -> {
            emailController.deleteEmail(emailId);
        });
        assertEquals("Not Found", exception.getMessage());
    }

    @Test
    void testConnect() {
        Connection connection = new Connection("test@example.com", "password");
        ResponseEntity<Connection> response = emailController.connect(connection);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(connection, response.getBody());
    }
    
}


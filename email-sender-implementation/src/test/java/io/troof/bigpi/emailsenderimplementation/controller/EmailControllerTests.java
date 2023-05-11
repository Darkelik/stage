package io.troof.bigpi.emailsenderimplementation.controller;

import io.troof.bigpi.emailsenderimplementation.model.EmailMessage;
import io.troof.bigpi.emailsenderimplementation.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmailControllerTests {
    @Mock
    private EmailService emailService;
    
    @InjectMocks
    private EmailController emailController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testSendEmail() {
        EmailMessage emailMessage = new EmailMessage("recipient@example.com", null, null, "Test Subject", "Test Message");
        ResponseEntity<EmailMessage> responseEntity = emailController.sendEmail(emailMessage);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(emailService).sendEmail(emailMessage);
    }
    
    @Test
    public void testGetAllEmails() {
        EmailMessage emailMessage1 = new EmailMessage("recipient@example.com", null, null, "Test Subject 1", "Test Message 1");
        EmailMessage emailMessage2 = new EmailMessage("recipient@example.com", null, null, "Test Subject 2", "Test Message 2");
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
        Optional<EmailMessage> emailMessage = Optional.of(new EmailMessage("recipient@example.com", null, null, "Test Subject", "Test Message"));
        when(emailService.getEmailById(1L)).thenReturn(emailMessage);
        ResponseEntity<EmailMessage> responseEntity = emailController.getEmailById(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(emailMessage, responseEntity.getBody());
    }
    
    @Test
    public void testDeleteEmail() throws Exception {
        ResponseEntity<String> responseEntity = emailController.deleteEmail(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(emailService).deleteEmail(1L);
    }
}


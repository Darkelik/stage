package io.troof.bigpi.emailsenderimplementation.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import io.troof.bigpi.emailsenderimplementation.model.EmailMessage;
import io.troof.bigpi.emailsenderimplementation.repository.EmailRepository;

public class EmailServiceImplTests {

    private EmailServiceImpl emailService;
    private EmailRepository emailRepositoryMock;

    @BeforeEach
    public void setUp() {
        emailRepositoryMock = mock(EmailRepository.class);
        emailService = new EmailServiceImpl();
        emailService.setRepository(emailRepositoryMock);
        emailService.setParameters("fredericvaz2016@gmail.com", "aprjqviwqydnurgc");
    }

    @Test
    public void testSendEmail() {
        String to = "recipient@example.com";
        String cc = "cc@example.com";
        String bcc = "bcc@example.com";
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

    // Add more tests as needed
}

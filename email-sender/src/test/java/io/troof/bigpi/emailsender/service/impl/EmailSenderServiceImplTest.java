package io.troof.bigpi.emailsender.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

class EmailSenderServiceImplTest {

  @Mock
  private JavaMailSender mailSender;

  private EmailSenderServiceImpl emailSenderService;

  @SuppressWarnings("deprecation")
  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    emailSenderService = new EmailSenderServiceImpl(mailSender);
  }

  @Test
  void testSendEmail() {
    String to = "test@example.com";
    String cc = "testcc@example.com";
    String bcc = "testbcc@example.com";
    String subject = "Test Subject";
    String message = "Test Message";

    emailSenderService.sendEmail(to, cc, bcc, subject, message);

    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(to);
    simpleMailMessage.setCc(cc);
    simpleMailMessage.setBcc(bcc);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(message);
    verify(mailSender, times(1)).send(simpleMailMessage);
  }
}



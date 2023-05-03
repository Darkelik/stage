package io.troof.bigpi.emailsender.controller;

import io.troof.bigpi.emailsender.resource.EmailMessage;
import io.troof.bigpi.emailsender.service.EmailSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** Contrôleur des envois d'email. */
@RestController
public class EmailController {

  private final EmailSenderService emailSenderService;

  public EmailController(EmailSenderService emailSenderService) {
    this.emailSenderService = emailSenderService;
  }

  /** Recevoir les informations. */
  @PostMapping("/send-email")
  public ResponseEntity<String> sendEmail(@RequestBody EmailMessage emailMessage) {
    String to = emailMessage.getTo();
    String cc = emailMessage.getCc();
    String bcc = emailMessage.getBcc();
    String subject = emailMessage.getSubject();
    String message = emailMessage.getMessage();

    this.emailSenderService.sendEmail(to, cc, bcc, subject, message);
    return ResponseEntity.ok("Success sending");
  }

  @RequestMapping(value = "/emails", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<EmailSenderService> getEmails() {
    return ResponseEntity.ok(emailSenderService);
  }
}

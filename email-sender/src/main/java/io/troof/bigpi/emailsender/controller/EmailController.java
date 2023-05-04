package io.troof.bigpi.emailsender.controller;

import io.troof.bigpi.emailsender.resource.EmailMessage;
import io.troof.bigpi.emailsender.service.EmailSenderService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** Contrôleur des envois d'email. */
@RestController
public class EmailController {

  List<EmailMessage> emails;
  
  private final EmailSenderService emailSenderService;

  public EmailController(EmailSenderService emailSenderService) {
    this.emailSenderService = emailSenderService;
    this.emails = new ArrayList<EmailMessage>();
  }

  /** Recevoir les informations. */
  @PostMapping("/send-email")
  public ResponseEntity<String> sendEmail(@RequestBody EmailMessage emailMessage) {
      
    try {
      String to = emailMessage.getTo();
      
      if (to == null || to == "") {
        return ResponseEntity.badRequest().body("'To' cannot be empty");
      }
      
      if (!EmailValidator.getInstance().isValid(to)) {
        return ResponseEntity.badRequest().body("Invalid email address");
      }
      
      String subject = emailMessage.getSubject();
      String message = emailMessage.getMessage();
      
      if (subject == null || subject == "") {
        return ResponseEntity.badRequest().body("'Subject' cannot be empty");
      }
      if (message == null || message == "") {
        return ResponseEntity.badRequest().body("'Message' cannot be empty");
      }
    
      String cc = emailMessage.getCc();
      String bcc = emailMessage.getBcc();

      this.emailSenderService.sendEmail(to, cc, bcc, subject, message);
      this.emails.add(emailMessage);
      return ResponseEntity.ok("Success sending");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
    }
  }

  @RequestMapping(value = "/emails", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<List<EmailMessage>> getEmails() {
    return ResponseEntity.ok(emails);
  }
}

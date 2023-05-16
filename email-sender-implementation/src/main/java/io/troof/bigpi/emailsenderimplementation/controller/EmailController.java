package io.troof.bigpi.emailsenderimplementation.controller;

import io.troof.bigpi.emailsenderimplementation.model.Connection;
import io.troof.bigpi.emailsenderimplementation.model.EmailMessage;
import io.troof.bigpi.emailsenderimplementation.service.impl.EmailServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/** Application Controller. */
@Controller
@RequestMapping("/api/emailsender")
public class EmailController {

  @Autowired
  private EmailServiceImpl service = new EmailServiceImpl();

  @GetMapping("/emails")
  public ResponseEntity<List<EmailMessage>> getAllEmails() {
    return ResponseEntity.ok().body(service.getRepository().findAll());
  }

  /** Get all information about specified email. */
  @GetMapping("/emails/{id}")
  public ResponseEntity<?> getEmailById(@PathVariable(value = "id") long emailId) {
    try {
      EmailMessage email = service.getEmailById(emailId)
          .orElseThrow(() -> new Exception("Not Found"));
      return ResponseEntity.ok().body(email);
    } catch (Exception e) {
      if (e.getMessage() == "Not Found") {
        return ResponseEntity.badRequest().body("email n°" + emailId + " doesn't exist.");
      } else {
        return ResponseEntity.badRequest().body("unknown error.");
      }
    }
  }

  /** Delete specified email from database. */
  @DeleteMapping("/emails/{id}")
  public ResponseEntity<String> deleteEmail(@PathVariable(value = "id") long emailId) {
    try {
      service.getEmailById(emailId).orElseThrow(() -> new Exception("Not Found"));
      service.deleteEmail(emailId);
      return ResponseEntity.ok().body("email n°" + emailId + " deleted successfully.");
    } catch (Exception e) {
      if (e.getMessage() == "Not Found") {
        return ResponseEntity.badRequest().body("email n°" + emailId + " doesn't exist.");
      } else {
        return ResponseEntity.badRequest().body("unknown error.");
      }
    }
  }

  @PutMapping("/connect")
  public ResponseEntity<Connection> connect(@Valid @RequestBody Connection connection) {
    service.setParameters(connection.getEmail(), connection.getPassword());
    return ResponseEntity.ok().body(connection);
  }

  @PostMapping("/send")
  public ResponseEntity<EmailMessage> sendEmail(@Valid @RequestBody EmailMessage email) {
    service.sendEmail(email);
    return ResponseEntity.ok().body(email);
  }

  public void setService(EmailServiceImpl service) {
    this.service = service;
  }
}

package io.troof.bigpi.emailsenderui.controller;

import io.troof.bigpi.emailsenderui.resource.AutoEmail;
import io.troof.bigpi.emailsenderui.resource.Connection;
import io.troof.bigpi.emailsenderui.resource.EmailMessage;
import io.troof.bigpi.emailsenderui.resource.SmallConnection;
import io.troof.bigpi.emailsenderui.resource.User;
import io.troof.bigpi.emailsenderui.service.impl.EmailServiceImpl;
import io.troof.bigpi.emailsenderui.service.impl.UserServiceImpl;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** Email sender controller. */
@RestController
public class EmailController {

  @Autowired
  private EmailServiceImpl emailService = new EmailServiceImpl();
  
  @Autowired
  private UserServiceImpl userService = new UserServiceImpl();

  @GetMapping("/emails")
  public ResponseEntity<List<AutoEmail>> getAllEmails() {
    return ResponseEntity.ok().body(emailService.getRepository().findAll());
  }

  /** Get all information about specified email. */
  @GetMapping("/emails/{id}")
  public ResponseEntity<?> getEmailById(@PathVariable(value = "id") String emailId) {
    try {
      AutoEmail email = emailService.getEmailById(emailId)
          .orElseThrow(() -> new Exception("Not Found"));
      return ResponseEntity.ok().body(email);
    } catch (Exception e) {
      if (e.getMessage() == "Not Found") {
        return ResponseEntity.badRequest()
          .body("Auto email with id " + emailId + " doesn't exist.");
      } else {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    }
  }

  /** Delete specified email from database. */
  @DeleteMapping("/emails/{id}")
  public ResponseEntity<String> deleteAutoEmail(@PathVariable(value = "id")String emailId) {
    try {
      emailService.getEmailById(emailId).orElseThrow(() -> new Exception("Not Found"));
      emailService.deleteAutoEmail(emailId);
      return ResponseEntity.ok().body("Auto email with id " + emailId + " deleted successfully.");
    } catch (Exception e) {
      if (e.getMessage() == "Not Found") {
        return ResponseEntity.badRequest()
          .body("Auto email with id " + emailId + " doesn't exist.");
      } else {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    }
  }

  /** POST method for registering a user. */
  @PostMapping("/register")
  public ResponseEntity<String> register(@Valid @RequestBody Connection connection) {
    User newUser = new User(connection);
    userService.register(newUser);
    return ResponseEntity.ok().body("User " + connection.getEmail() + " successfully registered.");
  }
  
  /** PUT method for setting user connection information. */
  @PutMapping("/connect")
  public ResponseEntity<String> connect(@Valid @RequestBody SmallConnection connection) {
    if (userService.getConnectedUser() != null) {
      return ResponseEntity.badRequest()
        .body(userService.getConnectedUser().getEmail() + " is already connected.");
    }
    boolean success = userService.connect(connection);
    if (success) {
      return ResponseEntity.ok().body(connection.getEmail() + " successfully connected.");
    } else {
      return ResponseEntity.badRequest().body("Wrong email or password.");
    }
  }

  /** Disconnecting a user. */
  @PutMapping("/disconnect")
  public ResponseEntity<String> disconnect() {
    if (userService.getConnectedUser() == null) {
      return ResponseEntity.badRequest().body("No one is connected.");
    }
    User user = userService.getConnectedUser();
    userService.disconnect();
    return ResponseEntity.ok().body(user.getEmail() + " successfully disconnected.");
  }

  /** Sending a regular e-mail. */
  @PostMapping("/send")
  public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailMessage email) {
    User user = userService.getConnectedUser();
    if (user == null) {
      return ResponseEntity.badRequest().body("No user is connected.");
    }
    emailService.initValues(user);
    emailService.sendEmail(email, userService.getConnectedUser().getEmail());
    return ResponseEntity.ok().body("Email successfully sent.");
  }
  
  /** Creating an auto e-mail. */
  @PostMapping("/create")
  public ResponseEntity<String> prepareAutoEmail(@Valid @RequestBody AutoEmail email) {
    emailService.saveAutoEmail(email);
    return ResponseEntity.ok()
      .body("Auto email with id " + email.getId() + " successfully created.");
  }

  /** Sending a prepared e-mail. */
  @PostMapping("/autosend")
  public ResponseEntity<?> sendAutoEmail(@Valid @RequestBody String id) {
    User user = userService.getConnectedUser();
    if (user == null) {
      return ResponseEntity.badRequest().body("No user is connected.");
    }
    try {
      AutoEmail email = emailService.getEmailById(id).orElseThrow(() -> new Exception("Not Found"));
      emailService.initValues(user);
      emailService.sendAutoEmail(email, userService.getConnectedUser().getEmail());
      return ResponseEntity.ok().body("Auto email with id " + id + " successfully sent.");
    } catch (Exception e) {
      if (e.getMessage() == "Not Found") {
        return ResponseEntity.badRequest().body("Auto email with id " + id + " doesn't exist.");
      } else {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    }
  }

  public void setEmailService(EmailServiceImpl emailService) {
    this.emailService = emailService;
  }
  
  public void setUserService(UserServiceImpl userService) {
    this.userService = userService;
  }
}

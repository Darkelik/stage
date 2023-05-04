package io.troof.bigpi.emailsender.controller;

import io.troof.bigpi.emailsender.resource.EmailMessage;
import io.troof.bigpi.emailsender.resource.LoginRequest;
import io.troof.bigpi.emailsender.resource.User;
import io.troof.bigpi.emailsender.service.EmailSenderService;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** Email sender controller. */
@RestController
public class EmailController {

  Hashtable<String, List<EmailMessage>> emails;
  
  List<User> userRepository;
  List<String> usedUsernames;
  List<String> usedAddresses;
  
  String loggedAddress;
  
  private final EmailSenderService emailSenderService;
  
  /** Constructeur. */
  public EmailController(EmailSenderService emailSenderService) {
    this.emailSenderService = emailSenderService;
    this.emails = new Hashtable<String, List<EmailMessage>>();
    this.userRepository = new ArrayList<User>();
    this.usedUsernames = new ArrayList<String>();
    this.usedAddresses = new ArrayList<String>();
    this.loggedAddress = "";
  }

  /** Receive information. */
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

      this.emailSenderService.sendEmail(to, cc, bcc, subject, message, loggedAddress);
      this.emails.get(loggedAddress).add(emailMessage);
      return ResponseEntity.ok("Success sending");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
    }
  }
  
  /** Register an account. */
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody User userDto) {
    if (usedUsernames.indexOf(userDto.getUsername()) != -1) {
      return ResponseEntity.badRequest().body("Username already exists");
    }
    if (!EmailValidator.getInstance().isValid(userDto.getAddress())) {
      return ResponseEntity.badRequest().body("Invalid email address");
    }
    User user = new User(userDto.getUsername(), userDto.getPassword(), userDto.getAddress());
    userRepository.add(user);
    usedUsernames.add(userDto.getUsername());
    usedAddresses.add(userDto.getAddress());
    emails.put(userDto.getAddress(), new ArrayList<EmailMessage>());
    return ResponseEntity.ok("User registered successfully");
  }
  
  /** Log an existing user in. */
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest log) {
    String address = connexionAttempt(log.getUsername(), log.getPassword());
    if (address.equals("")) {
      return ResponseEntity.badRequest().body("wrong username or password");
    }
    loggedAddress = address;
    return ResponseEntity.ok(address + " is now the logged email address");
  }
  
  /** Function for connection. */
  public String connexionAttempt(String username, String password) {
    for (User user : userRepository) {
      if (user.getUsername().equals(username)) {
        return user.getPassword().equals(password) ? user.getAddress() : "";
      }
    }
    return "";
  }

  /** Show emails sent by logged user. */
  @RequestMapping(value = "/emails", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<?> getEmails() {
    if (this.loggedAddress.equals("")) {
      return ResponseEntity.badRequest().body("no logged user");
    }
    return ResponseEntity.ok(emails.get(loggedAddress));
  }
}

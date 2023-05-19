package io.troof.bigpi.emailsenderui.controller;

import io.troof.bigpi.emailsenderui.resource.Connection;
import io.troof.bigpi.emailsenderui.resource.EmailMessage;
import io.troof.bigpi.emailsenderui.resource.SmallConnection;
import io.troof.bigpi.emailsenderui.resource.User;
import io.troof.bigpi.emailsenderui.service.impl.EmailServiceImpl;
import io.troof.bigpi.emailsenderui.service.impl.UserServiceImpl;

import javax.validation.Valid;

import java.util.List;
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
	  private EmailServiceImpl service = new EmailServiceImpl();
	  
	  @Autowired
	  private UserServiceImpl userService = new UserServiceImpl();

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

	  @PostMapping("/register")
	  public ResponseEntity<String> register(@Valid @RequestBody Connection connection){
		  User newUser = new User(connection);
		  userService.register(newUser);
		  return ResponseEntity.ok().body("User " + connection.getEmail() + " successfully registered.");
	  }
	  
	  /** Put method for setting user connection information. */
	  @PutMapping("/connect")
	  public ResponseEntity<String> connect(@Valid @RequestBody SmallConnection connection) {
	    if (userService.getConnectedUser() != null) {
	    	return ResponseEntity.badRequest().body(userService.getConnectedUser().getEmail() + " is already connected.");
	    }
	    boolean success = userService.connect(connection);
	    if (success) {
	    	return ResponseEntity.ok().body(connection.getEmail() + " successfully connected.");
	    } else {
	    	return ResponseEntity.badRequest().body(connection.getEmail() + " is not regitered.");
	    }
	  }
	  
	  @PutMapping("/disconnect")
	  public ResponseEntity<String> disconnect(){
		  if (userService.getConnectedUser() == null) {
			  return ResponseEntity.badRequest().body("No one is connected.");
		  }
		  User user = userService.getConnectedUser();
		  userService.disconnect();
		  return ResponseEntity.ok().body(user.getEmail() + " successfully disconnected.");
	  }

	  @PostMapping("/send")
	  public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailMessage email) {
		service.initValues(userService.getConnectedUser());
	    service.sendEmail(email);
	    return ResponseEntity.ok().body("email n°" + email.getId() + " successfully sent.");
	  }

	  public void setService(EmailServiceImpl service) {
	    this.service = service;
	  }
}

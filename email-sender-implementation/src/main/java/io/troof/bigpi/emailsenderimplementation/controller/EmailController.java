package io.troof.bigpi.emailsenderimplementation.controller;

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

import io.troof.bigpi.emailsenderimplementation.model.Connection;
import io.troof.bigpi.emailsenderimplementation.model.EmailMessage;
import io.troof.bigpi.emailsenderimplementation.service.impl.EmailServiceImpl;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/emailsender")
public class EmailController {
	
	@Autowired
	private EmailServiceImpl service = new EmailServiceImpl();
	
	@GetMapping("/emails")
	public List<EmailMessage> listAll(){
		return service.getRepository().findAll();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(value = "id") long emailId) throws Exception{
		service.getRepository().findById(emailId).orElseThrow(() -> new Exception("Not Found"));
		service.getRepository().deleteById(emailId);
		return ResponseEntity.ok().body("email n°" + emailId + " deleted successfully.");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmailMessage> getEmailById(@PathVariable(value = "id") long emailId) throws Exception{
		EmailMessage email = service.getRepository().findById(emailId).orElseThrow(() -> new Exception("Not Found"));
		return ResponseEntity.ok().body(email);
	}
	
	@PutMapping("/connect")
	public ResponseEntity<Connection> connect(@Valid @RequestBody Connection connection){
		service.setParameters(connection.getEmail(), connection.getPassword());
		return ResponseEntity.ok().body(connection);
	}
	
	@PostMapping("/send")
	public ResponseEntity<EmailMessage> sendEmail(@Valid @RequestBody EmailMessage email){
		service.sendEmail(email);
		return ResponseEntity.ok().body(email);
	}
}

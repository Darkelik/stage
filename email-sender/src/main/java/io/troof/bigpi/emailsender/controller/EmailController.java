package io.troof.bigpi.emailsender.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.troof.bigpi.emailsender.resource.EmailMessage;
import io.troof.bigpi.emailsender.service.EmailSenderService;

@RestController
public class EmailController {
	
	private final EmailSenderService emailSenderService;
	
	public EmailController(EmailSenderService emailSenderService) {
		this.emailSenderService = emailSenderService;
	}

	@PostMapping("/send-email")
	public ResponseEntity<String> sendEmail(@RequestBody EmailMessage emailMessage) {
		this.emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
		return ResponseEntity.ok("Success sending");
	}
	
	@RequestMapping(value = "/emails", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<EmailSenderService> getEmails() {
		return ResponseEntity.ok(emailSenderService);
	}
}

package io.troof.bigpi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import io.troof.bigpi.model.Email;

@RestController
public class EmailController {
	private List<Email> emails = createList();
	
	private List<Email> createList(){
		List<Email> tempEmails = new ArrayList<Email>();
		Email email1 = new Email();
		email1.setId("1");
		email1.setAdress("fredericvaz2016@gmail.com");
		email1.setCc("");
		email1.setBcc("");
		email1.setSubject("hello");
		email1.setBody("hello, this is a test");
		return tempEmails;
	}
	
	@RequestMapping(value = "/emails", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getEmail(@RequestParam("id") String id) {
	    if (id != null) {
	        Optional<Email> emailTemp = emails.stream().filter(e -> e.getId().equals(id)).findFirst();
	        if (emailTemp.isPresent()) {
	            String adress = emailTemp.get().getAdress();
	            String cc = emailTemp.get().getCc();
	            String bcc = emailTemp.get().getBcc();
	            String subject = emailTemp.get().getSubject();
	            String body = emailTemp.get().getBody();
	            String message = "Adress = " + adress + ", Cc = " + cc + ", Bcc = " + bcc + ", Subject = " + subject + ", Body = " + body;
	            return ResponseEntity.ok(message);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } else {
	        return ResponseEntity.ok(emails);
	    }
	}
	
	
	@DeleteMapping(path = {"/{id}"})
	public Email delete(@PathVariable("id") String adress) {
		Email deletedEmail = null;
		for (Email emailTemp : emails) {
			if (emailTemp.getAdress().equals(adress)) {
				emails.remove(emailTemp);
				deletedEmail = emailTemp;
				break;
			}
		}
		return deletedEmail;
	}
	
	@PostMapping
	public Email create(@RequestBody Email email) {
	    emails.add(email);
	    System.out.println(email);
	    return email;
	}
	
}

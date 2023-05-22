package io.troof.bigpi.emailsenderui.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AutoEmail")
public class AutoEmail {

	private String id;
	private EmailMessage message;
	
	public AutoEmail() {
		
	}
	
	public AutoEmail(String id, EmailMessage message) {
		this.id = id;
		this.message = message;
	}
	
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	@Column(name="email")
	public EmailMessage getMessage() {
		return message;
	}

	public void setMessage(EmailMessage message) {
		this.message = message;
	}
	
}

package io.troof.bigpi.emailsenderdatabase.resource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmailMessage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String message;
	
	public EmailMessage() {
	    
	  }
	  
	  /** Constructor. */
	  public EmailMessage(String to, String cc, String bcc, String subject, String message) {
	    this.to = to;
	    this.cc = cc;
	    this.bcc = bcc;
	    this.subject = subject;
	    this.message = message;
	  }

	  public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
	    return to;
	  }

	  public void setTo(String to) {
	    this.to = to;
	  }

	  public String getCc() {
	    return cc;
	  }

	  public void setCc(String cc) {
	    this.cc = cc;
	  }

	  public String getBcc() {
	    return bcc;
	  }

	  public void setBcc(String bcc) {
	    this.bcc = bcc;
	  }

	  public String getSubject() {
	    return subject;
	  }

	  public void setSubject(String subject) {
	    this.subject = subject;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }
}
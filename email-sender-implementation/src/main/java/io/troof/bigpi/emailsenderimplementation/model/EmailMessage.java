package io.troof.bigpi.emailsenderimplementation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "emails")
public class EmailMessage {
	
	private long id;
	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String message;
	
	public EmailMessage() {

    }

	public EmailMessage(String to, String cc, String bcc, String subject, String message) {
	    this.to = to;
	    this.cc = cc;
	    this.bcc = bcc;
	    this.subject = subject;
	    this.message = message;
	  }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "fromEmail", nullable = false)
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    @Column(name = "toEmail", nullable = false)
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }

    @Column(name = "cc", nullable = true)
    public String getCc() {
        return cc;
    }
    public void setCc(String cc) {
        this.cc = cc;
    }
    
    @Column(name = "bcc", nullable = true)
    public String getBcc() {
        return bcc;
    }
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }
    
    @Column(name = "subject", nullable = false)
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    @Column(name = "message", nullable = false)
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

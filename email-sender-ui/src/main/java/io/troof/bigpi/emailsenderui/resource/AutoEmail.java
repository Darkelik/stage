package io.troof.bigpi.emailsenderui.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** Defining prepared e-mails. */
@Entity
@Table(name = "AutoEmail")
public class AutoEmail {

  private String id;
  private String to;
  private String cc;
  private String bcc;
  private String subject;
  private String message;

  public AutoEmail() {
  
  }

  /** Contructor. */
  public AutoEmail(String id, String to, String cc, String bcc, String subject, String message) {
    this.id = id;
    this.to = to;
    this.cc = cc;
    this.bcc = bcc;
    this.subject = subject;
    this.message = message;
  }

  @Id
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Column(name = "toEmail")
  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  @Column(name = "cc")
  public String getCc() {
    return cc;
  }

  public void setCc(String cc) {
    this.cc = cc;
  }

  @Column(name = "bcc")
  public String getBcc() {
    return bcc;
  }

  public void setBcc(String bcc) {
    this.bcc = bcc;
  }

  @Column(name = "subject")
  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  @Column(name = "message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}

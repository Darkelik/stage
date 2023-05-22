package io.troof.bigpi.emailsenderui.resource;

/** EmailMessage class, used for schema and database storage. */
public class EmailMessage {

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

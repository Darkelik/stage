package io.troof.bigpi.emailsenderregistration.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/** Defining a user. */
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long userId;
  private String userName;
  private String userEmail;
  private String userPassword;
  private boolean isEnabled;
  
  public User() {

  }
  
  /** Constructor. */
  public User(Long id, String userName, String userEmail, String userPassword, boolean isEnabled) {
    this.userId = id;
    this.userName = userName;
    this.userEmail = userEmail;
    this.userPassword = userPassword;
    this.isEnabled = isEnabled;
  }
    
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }
}

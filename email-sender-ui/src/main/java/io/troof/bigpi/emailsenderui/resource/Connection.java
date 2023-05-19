package io.troof.bigpi.emailsenderui.resource;

/** Connection class (used only for connection schema). */
public class Connection {

  private String email;
  private String password;

  public Connection(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}


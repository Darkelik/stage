package io.troof.bigpi.emailsenderui.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** User class. */
@Entity
@Table(name = "users")
public class User {

  private long id;
  private String name;
  private String email;
  private String password;
  private String host;
  private int port;
  private String protocol;
  private String auth;
  private String startTls;
  private String debug;
  

  public User(Connection connection) {
	this.name = connection.getName();
	this.email = connection.getEmail();
	this.password = connection.getPassword();
	this.host = connection.getHost();
	this.port = connection.getPort();
	this.protocol = connection.getProtocol();
	this.auth = connection.getAuth();
	this.startTls = connection.getStartTls();
	this.debug = connection.getDebug();
  }


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
public long getId() {
	return id;
}



public void setId(long id) {
	this.id = id;
}


@Column(name = "name")
public String getName() {
	return name;
}



public void setName(String name) {
	this.name = name;
}


@Column(name = "email")
public String getEmail() {
	return email;
}



public void setEmail(String email) {
	this.email = email;
}


@Column(name = "password")
public String getPassword() {
	return password;
}



public void setPassword(String password) {
	this.password = password;
}


@Column(name = "host")
public String getHost() {
	return host;
}



public void setHost(String host) {
	this.host = host;
}


@Column(name = "port")
public int getPort() {
	return port;
}



public void setPort(int port) {
	this.port = port;
}


@Column(name = "protocol")
public String getProtocol() {
	return protocol;
}



public void setProtocol(String protocol) {
	this.protocol = protocol;
}


@Column(name = "auth")
public String getAuth() {
	return auth;
}



public void setAuth(String auth) {
	this.auth = auth;
}


@Column(name = "StartTls")
public String getStartTls() {
	return startTls;
}



public void setStartTls(String startTls) {
	this.startTls = startTls;
}


@Column(name = "debug")
public String getDebug() {
	return debug;
}



public void setDebug(String debug) {
	this.debug = debug;
}

  

}


package io.troof.bigpi.emailsenderui.resource;

/** Connection class (used for connection schema). */
public class Connection {

  private String name;
  private String email;
  private String password;
  private String host;
  private int port;
  private String protocol;
  private String auth;
  private String startTls;
  private String debug;
  

  

  public Connection(String name, String email, String password, String host, int port, String protocol, String auth,
		String starttls, String debug) {
	this.name = name;
	this.email = email;
	this.password = password;
	this.host = host;
	this.port = port;
	this.protocol = protocol;
	this.auth = auth;
	this.startTls = starttls;
	this.debug = debug;
  }


public String getName() {
	return name;
}



public void setName(String name) {
	this.name = name;
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


public String getHost() {
	return host;
}



public void setHost(String host) {
	this.host = host;
}


public int getPort() {
	return port;
}



public void setPort(int port) {
	this.port = port;
}


public String getProtocol() {
	return protocol;
}



public void setProtocol(String protocol) {
	this.protocol = protocol;
}


public String getAuth() {
	return auth;
}



public void setAuth(String auth) {
	this.auth = auth;
}


public String getStartTls() {
	return startTls;
}



public void setStartTls(String startTls) {
	this.startTls = startTls;
}


public String getDebug() {
	return debug;
}



public void setDebug(String debug) {
	this.debug = debug;
}

  

}


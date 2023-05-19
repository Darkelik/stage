package io.troof.bigpi.emailsenderui.resource;

public class AutoEmail {

	private String message;
	
	public AutoEmail() {
		
	}
	
	public AutoEmail(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

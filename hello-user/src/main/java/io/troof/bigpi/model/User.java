package io.troof.bigpi.model;

public class User {
	private int id;
	private String username;
	
	public User() {
		
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User test = (User)obj;
		return this.id == test.id;
	}
}

package io.troof.bigpi.model;

public class User {
	private String id;
	private String username;
	
	public User() {
		
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getId() {
		return id;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	
	public void setId(String id) {
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
}
